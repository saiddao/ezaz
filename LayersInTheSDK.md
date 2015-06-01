# Layers in the SDK #

The EzAz SDK is divided into several layers:

**Layer 1: A placeholder for a possible abstraction layer.** Layer 2: Create Requests and evaluating Responses.
**Layer 3: Communication with Policy Decision Points.**

So what about a Layer 1? Why is that missing? Layer 1 is a placeholder for one of more optional high-level application authorization layers. At this time no Layer 1 is defined yet. Since a potential Layer 1 is most likely very application specific, you could think of creating your own abstraction layer here that will be called by your application, and this abstraction layer will then call Layer 2. Or you just skip Layer 1 completely and call Layer 2 from your application.

As an application developer, you'll be interacting with both Layers. If you have your own Policy Decision Point, you may want to develop your own Layer 3 driver so that the SDK can communicate with your PDP. The SDK is defined in a pluggable manner so that you can mix and match your different layers. This is done through a configuration file, so that even a sysadmin could reconfigure the SDK.

This figure shows how the layers are organized:



![http://wiki.ezaz.googlecode.com/git/images/layers.png](http://wiki.ezaz.googlecode.com/git/images/layers.png)

Even though there are many boxes, it is not difficult to understand. At the top, where it says "Applications or Frameworks" imagine your application.

## Layer 1: The abstraction Layer ##

If you have defined your own abstraction Layer 1, then your application could call that Layer. Otherwise, ignore Layer 1 for now, since there is no default implementation for Layer 1 defined. You application will therefore directly call Layer 2.

## Layer 2: The Request/Response Layer ##

There are three boxes for Layer 2 in the diagram. The blue box is called "Data Model Interface (getters/setters) and depicts the Layer 2 "interfaces". These define how applications can call Layer 2. The green box is a generic implementation of Layer 2 that is shipped with this SDK. You could use it directly by instantiating its classes and working with them. However, there is a better way, as you will see in a moment.

Next to the green Layer 2 box, there is a red box called "Custom or Proprietary Implementation (optional)". This refers to a possible alternative implementation of Layer2 that someone else (or even you) could provide. To do that, someone (or you) would have to fully implement the interfaces defined in the blue box.

The idea is that any application, if it uses the interfaces to Layer 2, will work with any implementation of Layer 2. For many cases, the generic Layer 2 implementation that is shipped with the SDK will work well - it has been designed "for the middle road" in terms of functionality and speed. For some special cases you may want to use a different Layer 2 implementation. Perhaps an embedded PDP will define its own Layer 2 implementation that will allow for some performance benefits. Or perhaps an alternative Layer 2 implementation will have some additional features.

So now you probably understand why the Layer 2 implementation is pluggable. You could develop your application with the generic Layer 2 implementation, and use one of the provided Layer 3 dummy drivers to test. Then as your application is deployed, the sysadmins could wire EzAz to a particular PDP and through the config file provide the PDP driver as well as a particular Layer 2 implementation.

## Layer 3: The Driver Layer ##

Layer 3 implements the actual connection to a policy decision point. As with the rest of the SDK, it is conveniently pluggable. The only thing that the application and the SDK need to agree upon is the _handle_ for the PDP. The handle is just an identifier that your application uses to identify a PDP. THe config file then defines a configuration for every _handle_, i.e. the actual driver class and additional parameters for the driver.

If you have your own PDP, you could enable EzAz to work with it by providing your own driver that implements the PDPservice interface. There is a synchronous and an asynchronous calling interface. If your PDP only support a synchronous calling interface, you might make your life easier by just subclassing org.EzAz.Layer3Driver.AsyncEmulator.AsyncEmulator. FOr an example on how to create a (simple) driver, take a look at the class org.EzAz.Layer3Driver.DummyDriver.DummyDriver.

Now that you've learned about the different layers in the EzAz SDK, why not continue your journey by looking at the example code that will show how easy it actually is to bootstrap the SDK, create and send off a request, and evaluate the response.

