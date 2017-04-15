### Observer pattern

Simple and convinient way to create calls

#### Pull model

* Simple.
* Forces observer to update it state, but does not provide any information on which data changed
* Use if observed amount of data is small

#### Push model

* More complex
* Forces observer to update but provides information about data which changed
* However pushed data might be inconsistent by the time update(method is called :))

#### MVC - Model View Controller

* Not a web architecture :)
* Many controllers and many views for one model.
* Models were not business objects. Models were used by business objects to connect those objects to the mental model of the user
* `Model` - understands mental model of the user and the way in which the data would be transformed according to that model
* `View` - understand how to present data which were in that model in a way which would be intuitive for the user to see
* `Controller` - understood how to interpret intuitive gestures of the user and convert them to commands against the model

`View` objects would register with the appropriate model `Model`
`Controllers` would send commands to the `Model`.
If the data in the object changed, then the `Model` would update view


#### MVP/MVVC - Model View Presenter
