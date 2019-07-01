/**
 * Represents a document of any size and of any number of pages.
 */
public class Document extends Item
{
  private final int pages;

  /**
   * Constructs a document.
   *
   * @param name the name of the document
   * @param length the length of the document
   * @param width the width of the document
   * @param pages the number of pages in the document
   */
  public Document(String name, double length, double width, int pages)
  {
    super(name, length, width);
    if (pages <= 0)
      pages = 1;

    this.pages = pages;
  }

  /**
   * Gets the number of pages in the document.
   *
   * @return the number of pages
   */
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
