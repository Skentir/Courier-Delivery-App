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

  /**
   * Gets the type of the item.
   *
   * @return the type, in this case, Document
   */
  @Override
  public String getItemType()
  {
    return "Document";
  }

  /**
   * Gets the height or thickness of the document, which depends on the number of pages.
   *
   * @return the thickness of the document.
   */
  @Override
  public double getHeight()
  {
    return Math.ceil(pages / 25.0);
  }

  /**
   * Gets the weight of the document, dependent on the number of pages.
   *
   * @return the weight of the document
   */
  @Override
  public double getWeight()
  {
    return Math.ceil(pages / 25.0 * 0.2);
  }
}
