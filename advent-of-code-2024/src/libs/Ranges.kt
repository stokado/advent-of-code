package libs

infix fun Int.rangeOfSize(size: Int): IntRange {
    check(size >= 0) { "Size must be non-negative" }
    return this..<(this + size)
}