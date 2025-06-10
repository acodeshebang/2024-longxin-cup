file://<WORKSPACE>/src/main/CPU/Decode.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 205
uri: file://<WORKSPACE>/src/main/CPU/Decode.scala
text:
```scala
import chisel3._
import chisel3.util._
import Decode_define._
class Decode extends RawModule{
    val io = IO(new Decode_interface())
    

    val signal = MuxLookup(io.Instruction,Decode_signal_default)(@@)

    io.imm_option := signal(0)
    io.conditional_branch_option := signal(1)
    io.alu_option := signal(2)
    io.MemoryAccess_option := signal(3)
    io.alu_a_sel := signal(4)
    io.alu_b_sel := signal(5)
    io.pc_sel := signal(6)
    io.ram_r := signal(7)
    io.ram_w := signal(8)
    io.rd_en := signal(9)

}
```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:426)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0