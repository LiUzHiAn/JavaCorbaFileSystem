package corba;


/**
* corba/TFilePOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/


// all file names in server available to download
public abstract class TFilePOA extends org.omg.PortableServer.Servant
 implements corba.TFileOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("download", new java.lang.Integer (0));
    _methods.put ("upload", new java.lang.Integer (1));
    _methods.put ("listAllFile", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {

  /* fileData:?????A */
       case 0:  // corba/TFile/download
       {
         String fileName = in.read_string ();
         corba.MyDataHolder fileData = new corba.MyDataHolder ();
         boolean $result = false;
         $result = this.download (fileName, fileData);
         out = $rh.createReply();
         out.write_boolean ($result);
         corba.MyDataHelper.write (out, fileData.value);
         break;
       }


  /* fileData:?7??e?A */
       case 1:  // corba/TFile/upload
       {
         String fileName = in.read_string ();
         byte fileData[] = corba.MyDataHelper.read (in);
         boolean $result = false;
         $result = this.upload (fileName, fileData);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }


  /* ?h?@	?}??? */
       case 2:  // corba/TFile/listAllFile
       {
         corba.MyListHolder allFileNames = new corba.MyListHolder ();
         this.listAllFile (allFileNames);
         out = $rh.createReply();
         corba.MyListHelper.write (out, allFileNames.value);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/TFile:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public TFile _this() 
  {
    return TFileHelper.narrow(
    super._this_object());
  }

  public TFile _this(org.omg.CORBA.ORB orb) 
  {
    return TFileHelper.narrow(
    super._this_object(orb));
  }


} // class TFilePOA
