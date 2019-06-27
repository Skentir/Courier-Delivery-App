public class Document extends Item
{
  private final int pages;

  public Document(String name, double length, double width, int pages)
  {
    super(name, length, width);
    if (pages <= 0)
      pages = 1;

    this.pages = pages;
  }

  public int getPages()
  {
    return pages;
  }

  @Override
  public String getItemType()
  {
    return "Document";
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
