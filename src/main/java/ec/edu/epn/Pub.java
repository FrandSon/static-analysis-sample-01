package ec.edu.epn;

public class Pub {

  // Constantes Públicas de Identificadores de Bebidas (Exigido por PubPricesTest)
  public static final String ONE_BEER = "one beer";
  public static final String ONE_CIDER = "one cider";
  public static final String A_PROPER_CIDER = "proper cider";
  public static final String GT = "gt";
  public static final String BACARDI_SPECIAL = "bacardi special";

  // Constantes Privadas de Precios
  private static final int PRICE_ONE_BEER = 74;
  private static final int PRICE_ONE_CIDER = 103;
  private static final int PRICE_A_PROPER_CIDER = 110;
  private static final int PRICE_GT = 115;
  private static final int PRICE_BACARDI_SPECIAL = 127;

  public int computeCost(String drink, boolean student, int amount) {
    // Cláusula de Guarda: Límite de consumo para ciertas bebidas con alcohol fuerte
    if (amount > 2 && (GT.equals(drink) || ONE_CIDER.equals(drink) || BACARDI_SPECIAL.equals(drink))) {
      throw new IllegalArgumentException("Too many drinks, max 2.");
    }

    // Cláusula de Guarda: Validación de cantidad lógica
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0.");
    }

    int basePrice = getBasePrice(drink);
    double discountFactor = getDiscountFactor(drink, student);
    int finalPrice = (int) Math.round(basePrice * discountFactor);

    return finalPrice * amount;
  }

  private int getBasePrice(String drink) {
    if (ONE_BEER.equals(drink))
      return PRICE_ONE_BEER;
    if (ONE_CIDER.equals(drink))
      return PRICE_ONE_CIDER;
    if (A_PROPER_CIDER.equals(drink))
      return PRICE_A_PROPER_CIDER;
    if (GT.equals(drink))
      return PRICE_GT;
    if (BACARDI_SPECIAL.equals(drink))
      return PRICE_BACARDI_SPECIAL;

    throw new IllegalArgumentException("No such drink exists in this pub");
  }

  private double getDiscountFactor(String drink, boolean student) {
    // Descuento exclusivo del 10% en cerveza para estudiantes
    if (student && ONE_BEER.equals(drink)) {
      return 0.9;
    }
    return 1.0;
  }
}
