# SSHJExamples
### Description
This is repository conatins basic examples of how to use SSHJ library, for uploading files using SFTP.
The different examples, are for different authenticaion methods: password or key authentication, and remote or local server.

### Usage of key authentication
1. Generate public and private keys using the command ```ssh-keygen -a 1000 -b 4096 -o -t rsa```
2. Keys should be placed at ```~/.ssh/``` folder and you need to have two of them:
	- *~/.ssh/name_of_key (this is your private key)*
	- *~/.ssh/name_of_key.pub (this is your public key)*
3. Now, we’ll add public the key to authorized_keys **in the SFTP server**:
	- *cat ~/.ssh/name_of_key.pub >> ~/.ssh/authorized_keys*
4. In the code where you need to add the key location **in the SFTP client**, write the path of the generated private key.

### Usefull links
 - [Set mac as SFTP server](https://www.maciverse.com/how-to-turn-on-your-macs-sftp.html)
 - [Good explation about how to use public/private key](https://www.digitalocean.com/community/questions/ssh-server-refused-our-key)
 - [SSHJ Client library](https://www.javadoc.io/doc/com.hierynomus/sshj/0.23.0/net/schmizz/sshj/SSHClient.html)
