fun main() {
    val result = calculateCommission(cardType = "Maestro", previousMonthlyAmount = 8_100_000,
        transferAmount = 500_000)
//    val result = calculateCommission(cardType = "Visa", previousMonthlyAmount = 8_100_000,
//            transferAmount = 500_000)
    println("Коммиссия за перевод равна $result коп.")
}

fun calculateCommission(cardType: String = "Vk Pay", previousMonthlyAmount: Int = 0, transferAmount: Int): Int {
    return when (cardType) {
        "Mastercard", "Maestro" -> mastercardCommission(previousMonthlyAmount, transferAmount)
        "Visa", "Мир" -> visaCommission(transferAmount)
        else -> 0
    }
}

fun mastercardCommission(previousMonthlyAmount: Int, transferAmount: Int): Int {
    val maxMonthlyAmount = 7_500_000
    val commission = (transferAmount * 0.006 + 2_000).toInt()
    return if (previousMonthlyAmount + transferAmount <= maxMonthlyAmount) 0 else commission
}

fun visaCommission(transferAmount: Int): Int {
    val minCommission = 3_500
    val commissionOnCard = 0.0075
    val commissionFromAmount = (transferAmount * commissionOnCard).toInt()
    return if (commissionFromAmount > minCommission) commissionFromAmount else minCommission
}