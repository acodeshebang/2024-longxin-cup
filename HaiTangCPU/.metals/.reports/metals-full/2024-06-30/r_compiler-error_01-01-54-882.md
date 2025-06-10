file://<WORKSPACE>/src/main/CPU/Define_option.scala
### dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition <error> is defined in
  <WORKSPACE>/src/main/CPU/Define_option.scala
and also in
  <WORKSPACE>/src/main/CPU/Define_option.scala
One of these files should be removed from the classpath.

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 725
uri: file://<WORKSPACE>/src/main/CPU/Define_option.scala
text:
```scala
import chisel3._
import width._
object def_imm_option{
    val imm_default = 0.U(imm_option_width.W)
    val imm_2RI8 = 1.U(imm_option_width.W)
    val imm_2RI12 = 2.U(imm_option_width.W)
    val imm_2RI14 = 3.U(imm_option_width.W)
    val imm_2RI16 = 4.U(imm_option_width.W)
    val imm_2RI21 = 5.U(imm_option_width.W)
    val imm_I26 = 6.U(imm_option_width.W)
}
object def_alu_option{
    val alu_default = 0.U(Data_width.W)
    val alu_mov = 0.U(Data_width.W)
    val alu_add = 1.U(Data_width.W)
    val alu_sub = 2.U(Data_width.W)
    val alu_or = 3.U(Data_width.W)
    val alu_xor = 4.U(Data_width.W)
    val alu_and = 5.U(Data_width.W)
    val alu_srl = 6.U(Data_width.W)
    val alu_sll = 7.U(Data_width.W)
}

object D@@ecode_define{

}
```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition <error> is defined in
  <WORKSPACE>/src/main/CPU/Define_option.scala
and also in
  <WORKSPACE>/src/main/CPU/Define_option.scala
One of these files should be removed from the classpath.