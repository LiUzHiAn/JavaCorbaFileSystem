package corba;

/**
* corba/TFileHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��TFile.idl
* 2019��3��27�� ������ ����09ʱ12��11�� CST
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
