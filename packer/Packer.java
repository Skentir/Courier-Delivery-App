package packer;

import java.util.Collection;

public interface Packer {
    Collection<Packing> pack(Container container, PackItem[] items);
}
