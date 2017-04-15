### Command pattern - based on Uncle Bob e.25

`-------------<I>`
`|    Command   |`
`----------------`
`| +execute()   |`
`----------------`

Single interface with execute command

* Separates _what_ is done from _who_ does it. Decoupling actions from actors.
* Separates _what_ from _when_
* Can implement undo behaviour
    * An object can keep state of what it did during execute()
    * It can then undo based on this state

#### Motors and sensors

* Sensors have no idea what kind of device they are controlling.
* They are only telling Command to fire and it is the command who knows what to do.
* You can put command objects on list and execute them whenever appropriate

#### Actor Model
Simple idea that has been used for decades in systems that require thousands of threads in mem ory constrained environment


#### Use Cases as Classes
  * Just fetch UseCase objects from factory and execute.

