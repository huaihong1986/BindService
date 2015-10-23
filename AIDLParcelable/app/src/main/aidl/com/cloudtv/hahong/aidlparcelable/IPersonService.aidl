// IPersonService.aidl
package com.cloudtv.hahong.aidlparcelable;
import com.cloudtv.hahong.aidlparcelable.Person;
// Declare any non-default types here with import statements
 interface IPersonService {
       void save(inout Person person);
}
