# A simple Example #

Itching to get started? Let's take a look at a sample program that initializes the SDK, constructs a request, sends the request to a PDP and then evaluates the answer.

To start with, let's look at one of the example code files for Java: org.EzAz.examples.simpleRequest.Request1. You can find this in the directory org/EzAz/examples/simpleRequest.

## The config file ##

Before diving into the code, first look at the configuration properties file, sdk.properties. There are several sections commented - these are just examples on how to configure any additional PDPs and you can ignore them for now. The settings that are relevant for the example deal with two things:

# Set which Layer 2 implementation to use
# Configure a PDP with the handle "dummy".

The first section of the properties file selects which Layer 2 implementation to use:

```
# Set Layer 2 implementation
org.EzAz.layer2Implementation=org.EzAz.generic.Layer2
```

As you might have guessed, it depends to a package name that includes a Layer 2 compliant implementation. The SDK will - upon finding this configuration setting - attempt to load the class `org.EzAz.generic.Layer2.layer2Bootstrapper` and initialize the SDK with this layer. You must make sure that the class that is in the CLASSPATH of your application - otherwise an exception will be thrown since the class loader can't find the class.

The second relevant section of the properties file configures a PDP:

```
# Example for dummy driver
org.EzAz.pdp.dummy.instanceName=dummy
org.EzAz.pdp.dummy.driver=org.EzAz.Layer3Driver.DummyDriver.DummyDriver
```

There are at a minimum two properties that have to be given for each PDP:

| **Parameter** | **Description** |
|:--------------|:----------------|
| instanceName  | Name of the handle for the PDP. The application will need to refer to this handle. |
| driver        | The name of the class that implements the Layer 3 driver. In this example, it is the dummy driver. This dummy driver just returns a PERMIT response for every request. This class must implement the interface `org.EzAz.Layer3.PDPService` and must be on the class path. |

## The code ##

The code of the Request1.java example does this:

# Read in the config file and initialize the SDK
# Get the PDPservice to use (this is the connection to the PDP that will be used later)
# Create and prepare a request
# Send the Request to the PDP
# Receive the response
# Evaluate the response

### Initialization ###

The first step is the loading of the properties file and the initialization of the SDK. This happens in those lines of code:

```
		Properties prop=new Properties();
		// Load properties
		InputStream propFile = this.getClass().getClassLoader().getResourceAsStream("org/EzAz/examples/simpleRequest/sdk.properties");
        try {
        	prop.load(propFile);
        	PDPserviceFactory.initSDK(prop);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

In a nutshell, you load the properties from a file, using the class `java.util.Properties` and then you initialize the SDK by calling the static method `PDPserviceFactory.initSDK()` and passing in the properties as a parameter. This will wire the SDK to the desired Layer 2 implementation and load as well as initialize every PDP that is configured in the properties.

### Get a handle to the PDP ###

The demo application needs to know which PDP handle to use. This must be agreed between the properties file and the application. In the example provided, the PDP handle is hard-wired. In a well written application, the PDP handle would most likely come from some external configuration. The following line retrieves a PDPservice object from the handle:


```
		// Initialize connection to PDP (policy decision point)
		// called "dummy" and get a handle. This name is defined in
		// the properties file.
		// We need this handle to communicate with the PDP.
		PDPService pdp=PDPserviceFactory.getPDP("dummy");
```

### Create a request and populate attributes ###

Now a request is created with the following statement:

```
		// Now construct a new request.
		Request req=PDPserviceFactory.newRequest();
```

For adding attributes, use the interface AttributeHelper. A Layer 2 request is supposed to implement this interface as well, so you can cast it:

```
// Our request also implements the AttributeHelper interface.
		AttributeHelper h=(AttributeHelper)req;
```

Now you can populate attributes. In XACML, attributes belong to a category. In other words: a request has categories which have attributes.

```
		// Now create attributes on the request.
		h.addAttribute(AttributeEntity.CAT_SUBJECT,
				Identifier.create("subject-id"), null, "jack");
		h.addAttribute(AttributeEntity.CAT_ACTION,
				Identifier.create("action-id"), null, "print");
		h.addAttribute(AttributeEntity.CAT_RESOURCE,
				Identifier.create("resource-type"), null, "document");
		h.addAttribute(AttributeEntity.CAT_RESOURCE,
				Identifier.create("resource-id"), null, "TPSreport25.xls");
```

This will set the following attributes:

| **Category** | **Attribute ID** | **Value** |
|:-------------|:-----------------|:----------|
| SUBJECT      | Subject-id       | jack      |
| ACTION       | action-id        | print     |
| RESOURCE     | resource-type    | document  |
| RESOURCE     | resource-id      | TPSreport25.xls |

So this is a request from user _jack_ trying to _print_ a _document_ with id _TPSreport25.xls_.

### Send the request to the PDP and get a response ###

Before sending the request to the PDP, the example code calls a utility function to pretty print the request:

```
		// Print out the request
		genericLayer2Utils.prettyPrint(req);
```

This is done by the following code. The `evaluate()` function can throw an exception, so we need to catch it here.

```
		// Now call the PDP using the evaluate() function.
		Response resp;
		try {
			resp = pdp.evaluate(req);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
```

### Evaluating the response ###

The simplest way of evaluating a response is to check whether our request is permitted or not. In XACML, there are four decisions: **PERMIT**, **DENY**, **NOT APPLICABLE** and **INDETERMINATE**. **NOT APPLICABLE** means that the PDP cannot find a policy that applies to our request and hence no decision can be made. **INDETERMINATE** means that the PDP has encountered a processing error.

There is a convenience function that we can call on the result, named `isAllowed()`. This function needs an argument that is the _bias_, which means that if there is no clear **PERMIT** or **DENY**, the _bias_ will take precedence. It is also possible for a PDP to return multiple results in one response. In this case, the _bias_ will be returned if those multiple results yield different decisions.

```
// Check whether the response allows our request.
		System.out.println("IS ALLOWED? "+resp.isAllowed(false));
```

### Running the example program ###

You should see the following output:

```
PROPERTIES FOR: dummy
{org.EzAz.pdp.dummy.instanceName=dummy, org.EzAz.pdp.dummy.driver=org.EzAz.Layer3Driver.DummyDriver.DummyDriver}
Fetching driver: org.EzAz.pdp.dummy.driver
REQUEST
  CATEGORY: urn:oasis:names:tc:xacml:1.0:subject-category:access-subject
    ATTR: subject-id=jack (http://www.w3.org/2001/XMLSchema#string)
  CATEGORY: urn:oasis:names:tc:xacml:3.0:attribute-category:action
    ATTR: action-id=print (http://www.w3.org/2001/XMLSchema#string)
  CATEGORY: urn:oasis:names:tc:xacml:3.0:attribute-category:resource
    ATTR: resource-type=document (http://www.w3.org/2001/XMLSchema#string)
    ATTR: resource-id=TPSreport25.xls (http://www.w3.org/2001/XMLSchema#string)
IS ALLOWED? true
```