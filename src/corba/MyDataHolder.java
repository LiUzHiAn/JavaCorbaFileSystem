package corba;


/**
* corba/MyDataHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��TFile.idl
* 2019��3��27�� ������ ����09ʱ12��11�� CST
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
