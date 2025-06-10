file://<WORKSPACE>/src/main/CPU/Define_option.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 1295
uri: file://<WORKSPACE>/src/main/CPU/Define_option.scala
text:
```scala
import chisel3._
import chisel3.util._
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

object Decode_define{
    val Decode_signal_default = List()
    val addi_w = BitPat("0000001010??????????????????????")
    val lu12I_w =BitPat("0001010?????????????????????????")
    val add_w   =BitPat("00000000000100000???????????????")
    val st_w    =BitPat("0010101000??????????????????????")
    val ld_w    =BitPat("0010100010??????????????????????")
    val bne     =BitPat("010111??????????????????????????")

    val map = Array(
        addi_w -> List()
    )
}

object def_conditional_branch_option{
    val conditional_branch_default = 0.U(@@) 
    val conditional_bne = 0.U(conditional_branch_option_width.W)
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