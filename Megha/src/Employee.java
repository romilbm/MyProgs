public enum Employee {
  VANDANA(2),
  MINI_WILSON(3),

  MILAN_NAIK(4),

  KALPANA_MAMANIYA(5),

  MEENA_WALEKAR(6),

  SHALINI_MATTHEW(9),

  VANDITA_BHARADWAJ(10),

  PRIYANKA_CHAVAN(11),
  ANNAMMA_SAMUEL(12),
  KALAVATI_ADAM(15),
  SUPRIYA_PADAVE(16),
  SRIDEVI_RAVINDRAN(17),
  ALEYAMMA_PHILLIP(21),
  ANITA_BHOIA(22)
  ;


  public final Integer empNo;

  private Employee(Integer empNo) {
    this.empNo = empNo;
  }
  @Override
  public String toString() {
    String[] parts = this.name().split("_");
    String fname = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
    String lname = parts[1].substring(0, 1).toUpperCase() + parts[1].substring(1);

    return fname + " " + lname;
  }
}
