package org.gwgs.learningscalaz.day18


//1. monad for control flow
//2. interpreter for running the control flow
object FreeMonad {

  // Interpreter's AST (Abstract Syntax Tree)
  //   - an AST to express monadic operations;
  //   - an API to write interpreters that give meaning to this AST.
  sealed trait Free[F[_], A]
  final case class Point[F[_], A](a: A) extends Free[F, A]
  final case class Join[F[_], A](s: F[Free[F, A]]) extends Free[F, A]

}
