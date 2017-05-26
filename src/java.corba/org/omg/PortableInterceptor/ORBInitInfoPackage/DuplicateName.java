package org.omg.PortableInterceptor.ORBInitInfoPackage;


/**
* org/omg/PortableInterceptor/ORBInitInfoPackage/DuplicateName.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/corba/src/java.corba/share/classes/org/omg/PortableInterceptor/Interceptors.idl
* Wednesday, March 8, 2017 10:24:48 PM PST
*/

public final class DuplicateName extends org.omg.CORBA.UserException
{

  /** 
       * The name for which there was already an interceptor registered.
       */
  public String name = null;

  public DuplicateName ()
  {
    super(DuplicateNameHelper.id());
  } // ctor

  public DuplicateName (String _name)
  {
    super(DuplicateNameHelper.id());
    name = _name;
  } // ctor


  public DuplicateName (String $reason, String _name)
  {
    super(DuplicateNameHelper.id() + "  " + $reason);
    name = _name;
  } // ctor

} // class DuplicateName
