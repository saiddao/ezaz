
package com.axiomatics.delegent.pdpsimple.v1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "DelegentPDPPortType", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1")
@XmlSeeAlso({
    com.axiomatics.delegent.pdpsimple.v1.faults.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery2.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.invalidateattributecache.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery2.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery3.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instanceaccessquery3.ObjectFactory.class,
    oasis.names.tc.xacml._3_0.core.schema.wd_17.ObjectFactory.class,
    oasis.names.tc.xacml._2_0.context.schema.os.ObjectFactory.class,
    oasis.names.tc.xacml._2_0.policy.schema.os.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery3.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.instanceaccessquery2.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.getupdates.ObjectFactory.class,
    com.axiomatics.delegent.pdpsimple.v1.getversion.ObjectFactory.class
})
public interface DelegentPDPPortType {


    /**
     * 
     * @param request
     * @param instanceId
     * @return
     *     returns oasis.names.tc.xacml._2_0.context.schema.os.ResponseType
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceAccessQuery2", action = "InstanceAccessQuery2")
    @WebResult(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os")
    @RequestWrapper(localName = "InstanceAccessQuery2", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instanceaccessquery2.InstanceAccessQuery2")
    @ResponseWrapper(localName = "InstanceAccessQuery2Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instanceaccessquery2.InstanceAccessQuery2Response")
    public oasis.names.tc.xacml._2_0.context.schema.os.ResponseType instanceAccessQuery2(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceAccessQuery2")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os")
        oasis.names.tc.xacml._2_0.context.schema.os.RequestType request)
        throws PDPFault
    ;

    /**
     * 
     * @param request
     * @param instanceId
     * @return
     *     returns oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceAccessQuery3", action = "InstanceAccessQuery3")
    @WebResult(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
    @RequestWrapper(localName = "InstanceAccessQuery3", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instanceaccessquery3.InstanceAccessQuery3")
    @ResponseWrapper(localName = "InstanceAccessQuery3Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instanceaccessquery3.InstanceAccessQuery3Response")
    public oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType instanceAccessQuery3(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceAccessQuery3")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
        oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType request)
        throws PDPFault
    ;

    /**
     * 
     * @param store
     * @param request
     * @param instanceId
     * @return
     *     returns oasis.names.tc.xacml._2_0.context.schema.os.ResponseType
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceTestAccessQuery2", action = "InstanceTestAccessQuery2")
    @WebResult(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os")
    @RequestWrapper(localName = "InstanceTestAccessQuery2", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2.InstanceTestAccessQuery2")
    @ResponseWrapper(localName = "InstanceTestAccessQuery2Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2.InstanceTestAccessQuery2Response")
    public oasis.names.tc.xacml._2_0.context.schema.os.ResponseType instanceTestAccessQuery2(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery2")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os")
        oasis.names.tc.xacml._2_0.context.schema.os.RequestType request,
        @WebParam(name = "Store", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery2")
        List<com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2.InstanceTestAccessQuery2 .Store> store)
        throws PDPFault
    ;

    /**
     * 
     * @param store
     * @param request
     * @param instanceId
     * @return
     *     returns oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceTestAccessQuery3", action = "InstanceTestAccessQuery3")
    @WebResult(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
    @RequestWrapper(localName = "InstanceTestAccessQuery3", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery3.InstanceTestAccessQuery3")
    @ResponseWrapper(localName = "InstanceTestAccessQuery3Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery3.InstanceTestAccessQuery3Response")
    public oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType instanceTestAccessQuery3(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery3")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
        oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType request,
        @WebParam(name = "Store", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery3")
        List<com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery3.InstanceTestAccessQuery3 .Store> store)
        throws PDPFault
    ;

    /**
     * 
     * @param response
     * @param trace
     * @param request
     * @param instanceId
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceTraceAccessQuery2", action = "InstanceTraceAccessQuery2")
    @RequestWrapper(localName = "InstanceTraceAccessQuery2", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery2.InstanceTraceAccessQuery2")
    @ResponseWrapper(localName = "InstanceTraceAccessQuery2Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery2.InstanceTraceAccessQuery2Response")
    public void instanceTraceAccessQuery2(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery2")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os")
        oasis.names.tc.xacml._2_0.context.schema.os.RequestType request,
        @WebParam(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os", mode = WebParam.Mode.OUT)
        Holder<oasis.names.tc.xacml._2_0.context.schema.os.ResponseType> response,
        @WebParam(name = "Trace", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery2", mode = WebParam.Mode.OUT)
        Holder<com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery2.InstanceTraceAccessQuery2Response.Trace> trace)
        throws PDPFault
    ;

    /**
     * 
     * @param response
     * @param trace
     * @param request
     * @param instanceId
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceTraceAccessQuery3", action = "InstanceTraceAccessQuery3")
    @RequestWrapper(localName = "InstanceTraceAccessQuery3", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery3.InstanceTraceAccessQuery3")
    @ResponseWrapper(localName = "InstanceTraceAccessQuery3Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery3.InstanceTraceAccessQuery3Response")
    public void instanceTraceAccessQuery3(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery3")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
        oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType request,
        @WebParam(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", mode = WebParam.Mode.OUT)
        Holder<oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType> response,
        @WebParam(name = "Trace", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceAccessQuery3", mode = WebParam.Mode.OUT)
        Holder<com.axiomatics.delegent.pdpsimple.v1.instancetraceaccessquery3.InstanceTraceAccessQuery3Response.Trace> trace)
        throws PDPFault
    ;

    /**
     * 
     * @param response
     * @param store
     * @param trace
     * @param request
     * @param instanceId
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceTraceTestAccessQuery2", action = "InstanceTraceTestAccessQuery2")
    @RequestWrapper(localName = "InstanceTraceTestAccessQuery2", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery2.InstanceTraceTestAccessQuery2")
    @ResponseWrapper(localName = "InstanceTraceTestAccessQuery2Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery2", className = "com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery2.InstanceTraceTestAccessQuery2Response")
    public void instanceTraceTestAccessQuery2(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery2")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os")
        oasis.names.tc.xacml._2_0.context.schema.os.RequestType request,
        @WebParam(name = "Store", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery2")
        List<com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery2.InstanceTraceTestAccessQuery2 .Store> store,
        @WebParam(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os", mode = WebParam.Mode.OUT)
        Holder<oasis.names.tc.xacml._2_0.context.schema.os.ResponseType> response,
        @WebParam(name = "Trace", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery2", mode = WebParam.Mode.OUT)
        Holder<com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery2.InstanceTraceTestAccessQuery2Response.Trace> trace)
        throws PDPFault
    ;

    /**
     * 
     * @param response
     * @param store
     * @param trace
     * @param request
     * @param instanceId
     * @throws PDPFault
     */
    @WebMethod(operationName = "InstanceTraceTestAccessQuery3", action = "InstanceTraceTestAccessQuery3")
    @RequestWrapper(localName = "InstanceTraceTestAccessQuery3", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3.InstanceTraceTestAccessQuery3")
    @ResponseWrapper(localName = "InstanceTraceTestAccessQuery3Response", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3", className = "com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3.InstanceTraceTestAccessQuery3Response")
    public void instanceTraceTestAccessQuery3(
        @WebParam(name = "InstanceId", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3")
        String instanceId,
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
        oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType request,
        @WebParam(name = "Store", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3")
        List<com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3.InstanceTraceTestAccessQuery3 .Store> store,
        @WebParam(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", mode = WebParam.Mode.OUT)
        Holder<oasis.names.tc.xacml._3_0.core.schema.wd_17.ResponseType> response,
        @WebParam(name = "Trace", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3", mode = WebParam.Mode.OUT)
        Holder<com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3.InstanceTraceTestAccessQuery3Response.Trace> trace)
        throws PDPFault
    ;

    /**
     * 
     * @throws PDPFault
     */
    @WebMethod(operationName = "GetUpdates", action = "GetUpdates")
    @RequestWrapper(localName = "GetUpdates", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/GetUpdates", className = "com.axiomatics.delegent.pdpsimple.v1.getupdates.GetUpdates")
    @ResponseWrapper(localName = "GetUpdatesResponse", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/GetUpdates", className = "com.axiomatics.delegent.pdpsimple.v1.getupdates.GetUpdatesResponse")
    public void getUpdates()
        throws PDPFault
    ;

    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws PDPFault
     */
    @WebMethod(operationName = "GetVersion", action = "GetVersion")
    @WebResult(name = "Version", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/GetVersion")
    @RequestWrapper(localName = "GetVersion", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/GetVersion", className = "com.axiomatics.delegent.pdpsimple.v1.getversion.GetVersion")
    @ResponseWrapper(localName = "GetVersionResponse", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/GetVersion", className = "com.axiomatics.delegent.pdpsimple.v1.getversion.GetVersionResponse")
    public String getVersion()
        throws PDPFault
    ;

    /**
     * 
     * @param moduleParameter
     * @throws PDPFault
     */
    @WebMethod(operationName = "InvalidateAttributeCache", action = "InvalidateAttributeCache")
    @RequestWrapper(localName = "InvalidateAttributeCache", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InvalidateAttributeCache", className = "com.axiomatics.delegent.pdpsimple.v1.invalidateattributecache.InvalidateAttributeCache")
    @ResponseWrapper(localName = "InvalidateAttributeCacheResponse", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InvalidateAttributeCache", className = "com.axiomatics.delegent.pdpsimple.v1.invalidateattributecache.InvalidateAttributeCacheResponse")
    public void invalidateAttributeCache(
        @WebParam(name = "ModuleParameter", targetNamespace = "http://axiomatics.com/delegent/pdpsimple/v1/InvalidateAttributeCache")
        List<com.axiomatics.delegent.pdpsimple.v1.invalidateattributecache.InvalidateAttributeCache.ModuleParameter> moduleParameter)
        throws PDPFault
    ;

}