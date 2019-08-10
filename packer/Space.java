/**
* @author Kirsten Sison and Marc Tiburcio
* CCPROG3 S12A - Nathalie Lim Cheng
*/
package packer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Space
{
  private final Dimension dimensions;
  private final Point position;

  public Space(Dimension dimensions, Point position)
  {
    this.dimensions = dimensions;
    this.position = position;
  }

  public Dimension getDimensions()
  {
    return dimensions;
  }

  public Point getPosition()
  {
    return position;
  }

  /**
   * Checks if the space is empty.
   *
   * @return true if empty, false otherwise.
   */
  private boolean isEmpty()
  {
    return dimensions.isEmpty();
  }

  /**
   * Splits the space into subspaces to accommodate the item.
   *
   * @return the new subspaces created as a result of splitting the space.
   */
  public Collection<Space> split(ItemPlacement placement)
  {
    List<Space> spaces = new ArrayList<>();
    Dimension size = placement.getPlacement().getDimensions();
    Point position = placement.getPlacement().getPosition();
    spaces.add(new Space(
      new Dimension(
        this.dimensions.getWidth() - size.getWidth(),
        this.dimensions.getHeight(),
        this.dimensions.getLength()
      ),
      new Point(
        this.position.getX() + position.getX(),
        this.position.getY(),
        this.position.getZ()
      )
    ));
    spaces.add(new Space(
      new Dimension(
        this.dimensions.getWidth(),
        this.dimensions.getHeight() - size.getHeight(),
        this.dimensions.getLength()
      ),
      new Point(
        this.position.getX(),
        this.position.getY() + position.getY(),
        this.position.getZ()
      )
    ));
    spaces.add(new Space(
      new Dimension(
        this.dimensions.getWidth(),
        this.dimensions.getHeight(),
        this.dimensions.getLength() - size.getLength()
      ),
      new Point(
        this.position.getX(),
        this.position.getY(),
        this.position.getZ() + position.getZ()
      )
    ));
    spaces.add(new Space(
      new Dimension(
        this.dimensions.getWidth() + size.getWidth(),
        this.dimensions.getHeight(),
        this.dimensions.getLength() - size.getLength()
      ),
      new Point(
        this.position.getX() + position.getX(),
        this.position.getY(),
        this.position.getZ() + position.getZ()
      )
    ));

    spaces.removeIf(Space::isEmpty);
    return spaces;
  }
}
