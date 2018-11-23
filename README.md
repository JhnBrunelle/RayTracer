# Ray Tracer

![](/output.png)

This was created as a learning tool, to better grasp an understanding of RayTracing, ImagePlanes,
Phong's reflection model, anti-aliasing and other aspects of 3D Modeling/Rendering. The RayTracer currently features accurate lighting, texture support,anti-aliasing, and is able to output the ImagePlane of a 3D scene to a png file. 


## Dependencies
* This project uses [lombok](https://projectlombok.org/), a library which removes alot of the repetitive work that goes into developing with Java.
I mainly used it to avoid needing to write multiple constructors, getters and setters.
* This project also uses [Gradle](https://gradle.org/) to pull in lombok, and build 

## Running

To Build:
```
gradle build
```

To Run:
```
java -jar build/libs/raytracer-1.0.jar output.png
```

* This will create a new file in your current working directory, showing the output of the RayTracer
* The scene can be altered in the Runner.java file

## Future Plans:
* Ability to import models into a scene (ability to rasterize .obj files)
* 3D rendering a scene in real time, with ImagePlane controls
