public class Document extends Item
{
  private final int pages;

  public Document(double length, double width, int pages)
  {
    super(length, width);
    if (pages <= 0)
      pages = 1;

    this.pages = pages;
  }

  public int getPages()
  {
    return pages;
  }

  @Override
  public double getHeight()
  {
    return Math.ceil(pages / 25.0);
  }

  @Override
  public double getWeight()
  {
    return Math.ceil(pages / 25.0 * 0.2);
  }
}
