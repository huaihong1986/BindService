// IPersonService.aidl
package com.cloudtv.hahong.aidlparcelable;
import com.cloudtv.hahong.aidlparcelable.Person;
// Declare any non-default types here with import statements

interface IPersonService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
void save(inout Person person);
}
