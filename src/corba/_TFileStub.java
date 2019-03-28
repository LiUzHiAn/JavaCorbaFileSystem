package corba;


/**
* corba/_TFileStub.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从TFile.idl
* 2019年3月27日 星期三 下午09时12分11秒 CST
*/


// all file names in server available to download
public class _TFileStub extends org.omg.CORBA.portable.ObjectImpl implements corba.TFile
{


  /* fileData:?????A */
  public boolean download (String fileName, corba.MyDataHolder fileData)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("download", true);
                $out.write_string (fileName);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                fileData.value = corba.MyDataHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return download (fileName, fileData        );
            } finally {
                _releaseReply ($in);
            }
  } // download


  /* fileData:?7??e?A */
  public boolean upload (String fileName, byte[] fileData)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("upload", true);
                $out.write_string (fileName);
                corba.MyDataHelper.write ($out, fileData);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return upload (fileName, fileData        );
            } finally {
                _releaseReply ($in);
            }
  } // upload


  /* ?h?@	?}??? */
  public void listAllFile (corba.MyListHolder allFileNames)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listAllFile", true);
                $in = _invoke ($out);
                allFileNames.value = corba.MyListHelper.read ($in);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                listAllFile (allFileNames        );
            } finally {
                _releaseReply ($in);
            }
  } // listAllFile

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/TFile:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _TFileStub
