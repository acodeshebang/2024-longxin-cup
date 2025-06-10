file://<WORKSPACE>/src/main/CPU/ALU.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 852
uri: file://<WORKSPACE>/src/main/CPU/ALU.scala
text:
```scala
import chisel3._
import chisel3.util._
import width._
import def_alu_option._

class ALU extends RawModule {
  val io = IO(new ALU_interface())
  
  val alu_result_default = 0.S
  val alu_result_mov = io.in_data2.asSInt //note
  val alu_result_add = (io.in_data1.asUInt + io.in_data2.asUInt).asSInt
  val alu_result_sub = (io.in_data1.asUInt - io.in_data2.asUInt).asSInt
  val alu_result_or =  io.in_data1.asSInt | io.in_data2.asSInt
  val alu_result_and =  io.in_data1.asSInt & io.in_data2.asSInt
  val alu_result_xor =  io.in_data1.asSInt ^ io.in_data2.asSInt
  val alu_result_sll = ((io.in_data1.asUInt << io.in_data2.asUInt(4,0))).asSInt
  val alu_result_srl = (io.in_data1.asUInt >> io.in_data2.asUInt(4,0)).asSInt
  val alu_result_mul_tmp = Wire(SInt(64.W))
  alu_result_mul_tmp := io.in_data1.asSInt * io.in_data2.asSInt
  val alu_result_mul = (@@(io.in_data1.asSInt * io.in_data2.asSInt)(31,0)) //alu_result_mul_tmp(31,0).asSInt
  val alu_result_SLTI = Mux(io.in_data1.asSInt < io.in_data2.asSInt,1.S(32.W),0.S(32.W))
  val alu_result = MuxLookup(io.alu_option, 0.S(Data_width.W))(
    Seq(
        alu_default -> alu_result_default,
        alu_mov -> alu_result_mov,
        alu_add -> alu_result_add,
        alu_sub -> alu_result_sub,
        alu_or  -> alu_result_or,
        alu_and -> alu_result_and,  
        alu_xor -> alu_result_xor,
        alu_srl -> alu_result_srl,
        alu_sll -> alu_result_sll,
        alu_mul -> alu_result_mul,
        alu_SLTI -> alu_result_SLTI
    )
  )
  io.out_data := alu_result.asUInt
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
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:435)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0