package corba;


/**
* corba/TFileHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/


// all file names in server available to download
abstract public class TFileHelper
{
  private static String  _id = "IDL:corba/TFile:1.0";

  public static void insert (org.omg.CORBA.Any a, corba.TFile that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static corba.TFile extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (corba.TFileHelper.id (), "TFile");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static corba.TFile read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_TFileStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, corba.TFile value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static corba.TFile narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.TFile)
      return (corba.TFile)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._TFileStub stub = new corba._TFileStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static corba.TFile unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof corba.TFile)
      return (corba.TFile)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      corba._TFileStub stub = new corba._TFileStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
