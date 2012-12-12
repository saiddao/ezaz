package org.EzAz.Layer2;
/**
 * Copyright 2012 Felix Gaehtgens
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author felix
 * @version 1.0
 * @created 12-Dec-2012 23:53:39
 */
public interface Attribute {

	public static Identifier TYPE_BOOLEAN = Identifier.create ("http://www.w3.org/2001/XMLSchema/XMLSchema#boolean");
	public static Identifier TYPE_STRING = Identifier.create ("http://www.w3.org/2001/XMLSchema#string");
	public static Identifier TYPE_INTEGER = Identifier.create ("http://www.w3.org/2001/XMLSchema#integer");
	public static Identifier TYPE_DOUBLE = Identifier.create ("http://www.w3.org/2001/XMLSchema#double");
	public static Identifier TYPE_TIME = Identifier.create ("http://www.w3.org/2001/XMLSchema#time");
	public static Identifier TYPE_DATE = Identifier.create ("http://www.w3.org/2001/XMLSchema#date");
	public static Identifier TYPE_DATETIME = Identifier.create ("http://www.w3.org/2001/XMLSchema#datetime");
	public static Identifier TYPE_ANYURY = Identifier.create ("http://www.w3.org/2001/XMLSchema#anyURI");
	public static Identifier TYPE_HEXBINARY = Identifier.create ("http://www.w3.org/2001/XMLSchema#hexBinary");
	public static Identifier TYPE_BASE64BINARY = Identifier.create ("http://www.w3.org/2001/XMLSchema#base64Binary");
	public static Identifier TYPE_X500NAME = Identifier.create ("urn:oasis:names:tc:xacml:1.0:data-type:x500Name");
	public static Identifier TYPE_RFC822NAME = Identifier.create ("urn:oasis:names:tc:xacml:1.0:data-type:rfc822Name");
	public static Identifier TYPE_IPADDRESS = Identifier.create ("urn:oasis:names:tc:xacml:2.0:data-type:ipAddress");
	public static Identifier TYPE_DNSNAME = Identifier.create ("urn:oasis:names:tc:xacml:2.0:data-type:dnsName");

	/**
	 * Returns the ID of the Attribute.
	 */
	public Identifier getId();

	/**
	 * Returns the issuer of the Attribute.
	 */
	public String getIssuer();

	/**
	 * Return the ID of the type of the attribute.
	 */
	public Identifier getType();

	/**
	 * Returns the value of the attribute as an Object.
	 */
	public Object getValue();

	/**
	 * Indicates whether the attribute is of type Boolean.
	 */
	public boolean isBoolean();

	/**
	 * Indicates whether the attribute has multiple values.
	 */
	public boolean isMultiValue();

	/**
	 * Sets the ID of the attribute.
	 * 
	 * @param id    The ID of the attribute.
	 */
	public void setId(Identifier id);

	/**
	 * Sets the issues of the Attribute.
	 * 
	 * @param issuer    The issuer of the Attribute.
	 */
	public void setIssuer(String issuer);

	/**
	 * Sets the type ID of the attribute.
	 * 
	 * @param type    The ID of the type.
	 */
	public void setType(Identifier type);

	/**
	 * Sets the value of the attribute as an Object.
	 * 
	 * @param value    The attribute value.
	 */
	public void setValue(Object value);

	/**
	 * If this attribute belongs to a Request, the flag indicates whether the result
	 * returned by the PDP shall include this attribute (i.e. whether the result
	 * should have a copy of this attribute attached to it). If this attribute belongs
	 * to a Result, then this flag is set (because it was set in the attribute that
	 * was attached to the Request).
	 * 
	 * @param includeInResult
	 */
	public void setIncludeInResult(boolean includeInResult);

	/**
	 * Returns the getIncludeInResult flag. See setIncludeInResult().
	 */
	public boolean getIncludeInResult();

}
