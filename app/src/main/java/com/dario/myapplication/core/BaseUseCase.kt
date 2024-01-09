package com.dario.myapplication.core

interface UseCase<I, O> {
    suspend operator fun invoke(params: I): Result<O>
}