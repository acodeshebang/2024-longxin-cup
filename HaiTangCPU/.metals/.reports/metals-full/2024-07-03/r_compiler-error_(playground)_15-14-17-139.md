### java.lang.IndexOutOfBoundsException: -1 is out of bounds (min 0, max 2)

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 2.13.12
Classpath:
<WORKSPACE>/.bloop/out/playground/bloop-bsp-clients-classes/classes-Metals-p9HMJdLtSZa4uosqrqL5SQ== [exists ], <HOME>/.cache/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.9.10/semanticdb-javac-0.9.10.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/chipsalliance/chisel_2.13/7.0.0-M1/chisel_2.13-7.0.0-M1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/scala-lang/scala-reflect/2.13.12/scala-reflect-2.13.12.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/github/scopt/scopt_2.13/4.1.0/scopt_2.13-4.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/net/jcazevedo/moultingyaml_2.13/0.4.2/moultingyaml_2.13-0.4.2.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/json4s/json4s-native_2.13/4.0.6/json4s-native_2.13-4.0.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/apache/commons/commons-text/1.10.0/commons-text-1.10.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/io/github/alexarchambault/data-class_2.13/0.2.6/data-class_2.13-0.2.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/os-lib_2.13/0.9.1/os-lib_2.13-0.9.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/scala-lang/modules/scala-parallel-collections_2.13/1.0.4/scala-parallel-collections_2.13-1.0.4.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/upickle_2.13/3.1.0/upickle_2.13-3.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/chipsalliance/firtool-resolver_2.13/2.0.0/firtool-resolver_2.13-2.0.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/github/nscala-time/nscala-time_2.13/2.22.0/nscala-time_2.13-2.22.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/yaml/snakeyaml/1.26/snakeyaml-1.26.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/json4s/json4s-core_2.13/4.0.6/json4s-core_2.13-4.0.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/json4s/json4s-native-core_2.13/4.0.6/json4s-native-core_2.13-4.0.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/geny_2.13/1.0.0/geny_2.13-1.0.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/ujson_2.13/3.1.0/ujson_2.13-3.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/upack_2.13/3.1.0/upack_2.13-3.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/upickle-implicits_2.13/3.1.0/upickle-implicits_2.13-3.1.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/scala-lang/modules/scala-xml_2.13/2.2.0/scala-xml_2.13-2.2.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/scala-lang/modules/scala-collection-compat_2.13/2.11.0/scala-collection-compat_2.13-2.11.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/joda-time/joda-time/2.10.1/joda-time-2.10.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/joda/joda-convert/2.2.0/joda-convert-2.2.0.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/json4s/json4s-ast_2.13/4.0.6/json4s-ast_2.13-4.0.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/org/json4s/json4s-scalap_2.13/4.0.6/json4s-scalap_2.13-4.0.6.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar [exists ], <HOME>/.cache/coursier/v1/https/repo.scala-sbt.org/scalasbt/maven-releases/com/lihaoyi/upickle-core_2.13/3.1.0/upickle-core_2.13-3.1.0.jar [exists ]
Options:
-language:reflectiveCalls -deprecation -feature -Xcheckinit -Yrangepos -Xplugin-require:semanticdb


action parameters:
<NONE>


#### Error stacktrace:

```
scala.collection.mutable.ArrayBuffer.apply(ArrayBuffer.scala:106)
	scala.reflect.internal.Types$Type.findMemberInternal$1(Types.scala:1030)
	scala.reflect.internal.Types$Type.findMember(Types.scala:1035)
	scala.reflect.internal.Types$Type.memberBasedOnName(Types.scala:661)
	scala.reflect.internal.Types$Type.member(Types.scala:625)
	scala.tools.nsc.typechecker.Contexts$SymbolLookup.apply(Contexts.scala:1435)
	scala.tools.nsc.typechecker.Contexts$Context.lookupSymbol(Contexts.scala:1286)
	scala.tools.nsc.typechecker.Typers$Typer.typedIdent$2(Typers.scala:5572)
	scala.tools.nsc.typechecker.Typers$Typer.typedIdentOrWildcard$1(Typers.scala:5631)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6095)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6153)
	scala.tools.nsc.typechecker.Typers$Typer.typedSelectOrSuperCall$1(Typers.scala:6251)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6098)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6153)
	scala.tools.nsc.typechecker.Typers$Typer.$anonfun$typed1$32(Typers.scala:5112)
	scala.tools.nsc.typechecker.Typers$Typer.reportError$1(Typers.scala:5112)
	scala.tools.nsc.typechecker.Typers$Typer.onError$3(Typers.scala:5157)
	scala.tools.nsc.typechecker.Typers$Typer.normalTypedApply$1(Typers.scala:5184)
	scala.tools.nsc.typechecker.Typers$Typer.typedApply$1(Typers.scala:5194)
	scala.tools.nsc.typechecker.Typers$Typer.typed1(Typers.scala:6097)
	scala.tools.nsc.typechecker.Typers$Typer.typed(Typers.scala:6153)
	scala.tools.nsc.typechecker.Typers$Typer.computeType(Typers.scala:6242)
	scala.tools.nsc.typechecker.Namers$Namer.assignTypeToTree(Namers.scala:1137)
	scala.tools.nsc.typechecker.Namers$Namer.inferredValTpt$1(Namers.scala:1775)
	scala.tools.nsc.typechecker.Namers$Namer.valDefSig(Namers.scala:1788)
	scala.tools.nsc.typechecker.Namers$Namer.memberSig(Namers.scala:1976)
	scala.tools.nsc.typechecker.Namers$Namer.typeSig(Namers.scala:1926)
	scala.tools.nsc.typechecker.Namers$Namer$ValTypeCompleter.completeImpl(Namers.scala:944)
	scala.tools.nsc.typechecker.Namers$Namer$AccessorTypeCompleter.completeImpl(Namers.scala:968)
	scala.tools.nsc.typechecker.Namers$LockingTypeCompleter.complete(Namers.scala:2123)
	scala.tools.nsc.typechecker.Namers$LockingTypeCompleter.complete$(Namers.scala:2121)
	scala.tools.nsc.typechecker.Namers$TypeCompleterBase.complete(Namers.scala:2116)
	scala.reflect.internal.Symbols$Symbol.completeInfo(Symbols.scala:1565)
	scala.reflect.internal.Symbols$Symbol.info(Symbols.scala:1537)
	scala.meta.internal.pc.CompletionItemResolver.fullDocstring(CompletionItemResolver.scala:66)
	scala.meta.internal.pc.CompletionItemResolver.$anonfun$handleSymbol$2(CompletionItemResolver.scala:39)
	scala.meta.internal.pc.ItemResolver.enrichDocs(ItemResolver.scala:75)
	scala.meta.internal.pc.ItemResolver.enrichDocs$(ItemResolver.scala:14)
	scala.meta.internal.pc.CompletionItemResolver.enrichDocs(CompletionItemResolver.scala:8)
	scala.meta.internal.pc.CompletionItemResolver.handleSymbol(CompletionItemResolver.scala:40)
	scala.meta.internal.pc.CompletionItemResolver.resolve(CompletionItemResolver.scala:24)
	scala.meta.internal.pc.ScalaPresentationCompiler.$anonfun$completionItemResolve$1(ScalaPresentationCompiler.scala:320)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: -1 is out of bounds (min 0, max 2)