package com.paul.storage.index;

import java.util.Collection;

public interface UsernameIndexStorage<K> {

    K findIdByUsername(String username);

    Collection<String> getUsernames();
}
