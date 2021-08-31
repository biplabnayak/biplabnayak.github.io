# Segmented code cache
* Non-Method code
* Profiled Code
* Non- Profiled Code

# App CDS (Class Data Sharing)
* We need to specifically enable the support for application classes with -XX:+UseAppCDS, otherwise will limit ourselves to jdk classes only

# Reserved stack areas for critical sections
The JVM actually delays StackOverflowError, or at least attempts to, while
critical sections are executing. In order to capitalize on this new schema, methods must
be annotated with the following:
`jdk.internal.vm.annotation.ReservedStackAccess`
When a method has this annotation and a StackOverflowError condition
exists, temporary access to the reserved memory space is granted. The new process is, at a
high-level of abstraction, presented as follows:

# Diamond operator <> can be used with anonymous  inner classes
# underscore(_) is no longer a valid identifier. but can be used in an identifier