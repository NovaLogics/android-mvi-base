package novalogics.android.mvibase.core.util

/**
 * Returns a user-friendly error message from a Throwable.
 */
fun Throwable.getErrorMessage(): String {
    return this.localizedMessage ?: "An unknown error occurred"
}

/**
 * Returns an empty string.
 */
fun Any.emptyString(): String = ""
