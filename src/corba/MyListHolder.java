package corba;


/**
* corba/MyListHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��TFile.idl
* 2019��3��27�� ������ ����09ʱ12��11�� CST
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
