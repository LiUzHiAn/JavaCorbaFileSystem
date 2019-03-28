package corba;


/**
* corba/MyDataHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/

public final class MyDataHolder implements org.omg.CORBA.portable.Streamable
{
  public byte value[] = null;

  public MyDataHolder ()
  {
  }

  public MyDataHolder (byte[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.MyDataHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.MyDataHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.MyDataHelper.type ();
  }

}
