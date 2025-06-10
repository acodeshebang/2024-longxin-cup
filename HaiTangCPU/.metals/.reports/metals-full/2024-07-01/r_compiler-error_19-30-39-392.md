file://<WORKSPACE>/src/main/CPU/Decode.scala
### dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition alu_a_sel is defined in
  <WORKSPACE>/src/main/CPU/interface.scala
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
offset: 677
uri: file://<WORKSPACE>/src/main/CPU/Decode.scala
text:
```scala
import chisel3._
import chisel3.util._
import Decode_define._
class Decode extends RawModule{
    val io = IO(new Decode_interface())
    

    val signal = MuxLookup(io.Instruction,Decode_signal_default)(map)

    io.imm_option := signal(0)
    io.conditional_branch_option := signal(1)
    io.alu_option := signal(2)
    io.MemoryAccess_option := signal(3)
    io.GRGDataSel_option := signal(4)
    io.alu_a_sel := signal(5)
    io.alu_b_sel := signal(6)
    io.pc_sel := signal(7)
    io.ram_r := signal(8)
    io.ram_w := signal(9)
    io.rd_en := signal(10)

    io.rk_addr := io.Instruction(14,10)
    io.rj_addr := io.Instruction(9,5)
    io.rd_addr := io.Instruction(4,@@0)
    
}
```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition alu_a_sel is defined in
  <WORKSPACE>/src/main/CPU/interface.scala
and also in
  <WORKSPACE>/src/main/CPU/Define_option.scala
One of these files should be removed from the classpath.