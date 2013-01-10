package org.EzAz.Layer2;
/**
 * Copyright 2012-2013 Felix Gaehtgens
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
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author felix
 * @version 1.0
 * @created 10-Jan-2013 17:55:56
 */
public class Identifier implements Comparable<Identifier>, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1254860314338014227L;
	URI uri;

	/**
	 * 
	 * @param uri    uri
	 */
	public Identifier(URI uri){
		super();
		this.uri = uri;
	}

	public Identifier(){

	}

	/**
	 * @return
	 * @see java.net.URI#compareTo(java.net.URI)
	 * 
	 * @param that    that
	 */
	public int compareTo(Identifier that){
		return uri.compareTo(that.uri);
	}

	/**
	 * @return
	 * @see java.net.URI#equals(java.lang.Object)
	 * 
	 * @param ob    ob
	 */
	public boolean equals(Object ob){
				if (ob instanceof Identifier)
					return uri.equals(((Identifier) ob).uri);
				else
					return uri.equals(ob);
	}

	/**
	 * @return
	 * @see java.net.URI#getAuthority()
	 */
	public String getAuthority(){
		return uri.getAuthority();
	}

	/**
	 * @return
	 * @see java.net.URI#getFragment()
	 */
	public String getFragment(){
		return uri.getFragment();
	}

	/**
	 * @return
	 * @see java.net.URI#getHost()
	 */
	public String getHost(){
		return uri.getHost();
	}

	/**
	 * @return
	 * @see java.net.URI#getPath()
	 */
	public String getPath(){
		return uri.getPath();
	}

	/**
	 * @return
	 * @see java.net.URI#getPort()
	 */
	public int getPort(){
		return uri.getPort();
	}

	/**
	 * @return
	 * @see java.net.URI#getQuery()
	 */
	public String getQuery(){
		return uri.getQuery();
	}

	/**
	 * @return
	 * @see java.net.URI#getRawAuthority()
	 */
	public String getRawAuthority(){
		return uri.getRawAuthority();
	}

	/**
	 * @return
	 * @see java.net.URI#getRawFragment()
	 */
	public String getRawFragment(){
		return uri.getRawFragment();
	}

	/**
	 * @return
	 * @see java.net.URI#getRawPath()
	 */
	public String getRawPath(){
		return uri.getRawPath();
	}

	/**
	 * @return
	 * @see java.net.URI#getRawQuery()
	 */
	public String getRawQuery(){
		return uri.getRawQuery();
	}

	/**
	 * @return
	 * @see java.net.URI#getRawSchemeSpecificPart()
	 */
	public String getRawSchemeSpecificPart(){
		return uri.getRawSchemeSpecificPart();
	}

	/**
	 * @return
	 * @see java.net.URI#getRawUserInfo()
	 */
	public String getRawUserInfo(){
		return uri.getRawUserInfo();
	}

	/**
	 * @return
	 * @see java.net.URI#getScheme()
	 */
	public String getScheme(){
		return uri.getScheme();
	}

	/**
	 * @return
	 * @see java.net.URI#getSchemeSpecificPart()
	 */
	public String getSchemeSpecificPart(){
		return uri.getSchemeSpecificPart();
	}

	/**
	 * @return
	 * @see java.net.URI#getUserInfo()
	 */
	public String getUserInfo(){
		return uri.getUserInfo();
	}

	/**
	 * @return
	 * @see java.net.URI#hashCode()
	 */
	public int hashCode(){
		return uri.hashCode();
	}

	/**
	 * @return
	 * @see java.net.URI#isAbsolute()
	 */
	public boolean isAbsolute(){
		return uri.isAbsolute();
	}

	/**
	 * @return
	 * @see java.net.URI#isOpaque()
	 */
	public boolean isOpaque(){
		return uri.isOpaque();
	}

	/**
	 * @return
	 * @see java.net.URI#normalize()
	 */
	public URI normalize(){
		return uri.normalize();
	}

	/**
	 * @return
	 * @see java.net.URI#parseServerAuthority()
	 * @exception URISyntaxException URISyntaxException
	 */
	public URI parseServerAuthority()
	  throws URISyntaxException{
		return uri.parseServerAuthority();
	}

	/**
	 * @return
	 * @see java.net.URI#relativize(java.net.URI)
	 * 
	 * @param uri    uri
	 */
	public URI relativize(URI uri){
		return uri.relativize(uri);
	}

	/**
	 * @return
	 * @see java.net.URI#resolve(java.lang.String)
	 * 
	 * @param str    str
	 */
	public URI resolve(String str){
		return uri.resolve(str);
	}

	/**
	 * @return
	 * @see java.net.URI#resolve(java.net.URI)
	 * 
	 * @param uri    uri
	 */
	public URI resolve(URI uri){
		return uri.resolve(uri);
	}

	/**
	 * @return
	 * @see java.net.URI#toASCIIString()
	 */
	public String toASCIIString(){
		return uri.toASCIIString();
	}

	/**
	 * @return
	 * @see java.net.URI#toString()
	 */
	public String toString(){
		return uri.toString();
	}

	/**
	 * @return
	 * @see java.net.URI#toURL()
	 * @exception MalformedURLException MalformedURLException
	 */
	public URL toURL()
	  throws MalformedURLException{
		return uri.toURL();
	}

	/**
	 * 
	 * @param s    s
	 */
	public static Identifier create(String s){
		URI uri = URI.create(s);
		Identifier i = new Identifier();
		i.uri = uri;
		return i;
	}

}