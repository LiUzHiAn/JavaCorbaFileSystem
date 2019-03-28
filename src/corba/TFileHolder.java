package corba;

/**
* corba/TFileHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/


// all file names in server available to download
public final class TFileHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.TFile value = null;

  public TFileHolder ()
  {
  }

  public TFileHolder (corba.TFile initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.TFileHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.TFileHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.TFileHelper.type ();
  }

}
