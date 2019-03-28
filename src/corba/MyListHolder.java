package corba;


/**
* corba/MyListHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/

public final class MyListHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public MyListHolder ()
  {
  }

  public MyListHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.MyListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.MyListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.MyListHelper.type ();
  }

}
