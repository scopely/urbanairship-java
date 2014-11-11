# Urban Airship API Client for Java 8
Copyright Scopely, Inc. 2014.  
Distributed under the Apache 2.0 License. See LICENSE.md for details.

This client is not complete. For the moment it only handles Tag and Segment
management.

## Basic Usage
Build a client with `ClientBuilder`:
```java
RestAdapter client = new ClientBuilder()
    .setCredentials("MY APP KEY", "MY APP SECRET")
    .build();
```

The builder also accepts log level and API endpoint:
```java
builder.setEndpoint("http://localhost:3000"); // a local dev proxy
builder.setLogLevel(RestAdapter.LogLevel.FULL); // include all details
```

### Accessing APIs
Create the desired API objects with your built client:
```java
Segments segments = client.create(Segments.class);
// and/or
Tags tags = client.create(Tags.class);
```

These interfaces map closely to the actual API, so
[the official API docs](http://docs.urbanairship.com/reference/api/v3/index.html)
are a good point of reference on what methods do.

## Contributing
This library is written using [Retrofit](http://square.github.io/retrofit/).
That means it's easy to describe more endpoints. Describe a set of calls in
an `Interface` and Retrofit lets the user build a client straight from that.

Github's Pull Request system works well if you would like to contribute. Simply
fork this repository, add some APIs, and submit a pull request for review.
