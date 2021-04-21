
package com.peter.ziska.demoapp.base.either

/**
 * Credits to https://articles.caster.io/android/handling-optional-errors-using-kotlin-sealed-classes/
 *
 * The Either type represents values with two possibilities: [Left] or [Right].
 * [Left] represents failure and [Right] success (by convention)
 */
sealed class Either<out L, out R> {
    data class Left<L>(val l: L) : Either<L, Nothing>()
    data class Right<R>(val r: R) : Either<Nothing, R>()

    val isLeft get() = this is Left<L>
    val isRight get() = this is Right<R>
}

inline fun <L, R> Either<L, R>.fold(fnL: (L) -> Any, fnR: (R) -> Any): Any =
    when (this) {
        is Either.Left -> fnL(l)
        is Either.Right -> fnR(r)
    }


inline fun <NR, L, R> Either<L, R>.flatMap(fnR: (R) -> Either<L, NR>): Either<L, NR> =
    when (this) {
        is Either.Left -> Either.Left(l)
        is Either.Right -> fnR(r)
    }

inline fun <L, R> Either<L, R>.withRight(fnR: (R) -> Unit): Either<L, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> {
            fnR(r)
            this
        }
    }

inline fun <L, R> Either<L, R>.withLeft(fnL: (L) -> Unit): Either<L, R> =
    when (this) {
        is Either.Left -> {
            fnL(l)
            this
        }
        is Either.Right -> this
    }

inline fun <L, R> Either<L, R>.onLeft(fnL: (Either.Left<L>) -> Nothing): R =
    when (this) {
        is Either.Left -> fnL(this)
        is Either.Right -> r
    }


inline fun <L, R> Either<L, R>.onRight(fnR: (Either.Right<R>) -> Nothing): L =
    when (this) {
        is Either.Left -> l
        is Either.Right -> fnR(this)
    }
