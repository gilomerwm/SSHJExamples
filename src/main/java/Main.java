import java.io.IOException;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;

public class Main {

	/**********Local details*********/
	final static String fileName = "1.txt";
	final static String localPassword = "****";
	final static String src = "/Users/omerg/Documents/workmarket/SshjClient/filesForTests/";
	final static String localDst = src+"uploadDest/";
	final static String localHost = "localhost";
	final static String privateKeyLocation = "/Users/omerg/.ssh/macKey";
	public static final String localUsername = "omerg";
	/********** Remote details *********/
	final static String remoteHost = "192.168.1.6";
	final static String remoteDst = "C:\\Users\\gilom\\Downloads\\test\\";
	final static String remoteUsername = "gilom";
	final static String remotePassword = "****";

	public static void main(String[] args) throws IOException {
		sftpRemoteUsingKey();
		sftpRemoteUsingPassword();
		sftpLocalUsingKey();
		sftpLocalUsingPassword();
	}

	public static void sftpRemoteUsingPassword() throws IOException {
		final SSHClient client = new SSHClient();
		client.loadKnownHosts();
		try {
			client.addHostKeyVerifier(new PromiscuousVerifier());
			client.connect(remoteHost);
			client.authPassword(remoteUsername, remotePassword);
			final SFTPClient sftp = client.newSFTPClient();
			try {
				sftp.put(new FileSystemFile(src+fileName), remoteDst);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
			finally {
				sftp.close();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		} finally {
			client.disconnect();
		}
	}

	public static void sftpLocalUsingKey() throws IOException {
		final SSHClient ssh = new SSHClient();
		ssh.loadKnownHosts();
		ssh.addHostKeyVerifier(new PromiscuousVerifier());
		ssh.connect(localHost);
		try {
			ssh.authPublickey(localUsername, privateKeyLocation);
			final SFTPClient sftp = ssh.newSFTPClient();
			try {
				sftp.put(new FileSystemFile(src+fileName), localDst+fileName);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			finally {
				sftp.close();
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			ssh.disconnect();
		}
	}

	public static void sftpLocalUsingPassword() throws IOException {
		final SSHClient ssh = new SSHClient();
		ssh.loadKnownHosts();
		ssh.addHostKeyVerifier(new PromiscuousVerifier());
		ssh.connect(localHost);
		try {
			ssh.authPassword(localUsername, localPassword);
			final SFTPClient sftp = ssh.newSFTPClient();
			try {
				sftp.put(new FileSystemFile(src+fileName), localDst+fileName);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			finally {
				sftp.close();
			}

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			ssh.disconnect();
		}
	}

	public static void sftpRemoteUsingKey() throws IOException {
		final SSHClient client = new SSHClient();
		client.loadKnownHosts();
		try {
			client.addHostKeyVerifier(new PromiscuousVerifier());
			client.connect(remoteHost);
			client.authPublickey(remoteUsername, privateKeyLocation);

			final SFTPClient sftp = client.newSFTPClient();
			try {
				sftp.put(new FileSystemFile(src+fileName), remoteDst);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
			finally {
				sftp.close();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		} finally {
			client.disconnect();
		}
	}

}
