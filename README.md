# &#x1f9f6; Crochet &#x1f9f6; - Android application designed to help you with your crochet project

This is a work in progress; the only functionality it currently contains is a counter that can be controlled using voice commands: "add" and "back".
The main objective of this sample is to demonstrate a modern, scalable app design utilizing a multimodule architecture.

Convention plugins were used to accelerate the development of new features, reduce boilerplate, and make Gradle configuration easier to understand, thanks to the declarative nature of Gradle plugins.

Feature modules are dependency-free, thanks to the use of a dependency injection framework (Koin with annotations in this case).
These modules declare dependencies as interfaces, allowing the developer to fully focus on business requirements for a given feature.
Complex non-business logic is intentionally moved to modules with a lower-level abstraction.

Core modules are responsible for managing specific components, such as voice recognition or notifications.
They depend on each other, but this is kept to a minimum.
Typically application-specific, these modules are not easily reusable across other applications within similar business domains.

Core modules and feature modules are separated by the bridge, the app module.
The dependencies are "wired" here. Aside from that, this is where most setup takes place.
