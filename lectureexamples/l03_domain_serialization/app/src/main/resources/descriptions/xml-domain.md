# Domain Description: XML

Extensible Markup Language (XML) is a markup language and file format for storing, transmitting, and reconstructing arbitrary data. It defines a set of rules for encoding documents in a format that is both human-readable and machine-readable.
The original 1.0 specification dates back to 1998, is governed by the World Wide Web Consortium (W3C),
and is based on the earlier [Standard Generalized Markup Language (SGML)](https://en.wikipedia.org/wiki/Standard_Generalized_Markup_Language).

The main purpose of XML is serialization, i.e. storing, transmitting, and reconstructing arbitrary data.
As a markup language, XML labels, categorizes, and structurally organizes information.
XML tags represent the data structure and contain metadata. 

An XML file is also called a _document_.
A document has exactly one _root element_.
A XML element has name and content.
Names must not be empty and do not contain spaces.
The name of the element is
given by the respective XML tag. 
The content of an element is a list of _nodes_.
A Node is either an element or a _text node_, which simply
represents arbitrary text content (strings).
The content list of an element must not contain two text nodes beside each other.
But, it is allowed to have multiple element nodes (also with the same tag name) beside each other,
or to mix element and text nodes alternated (mixed content).
Elements may have attributed, which represents a list of key-value pairs, which are strings.
Elements and/or Attributes may have a _namespace_. 
A namespace is a [URL](https://developer.mozilla.org/en-US/docs/Learn/Common_questions/Web_mechanics/What_is_a_URL), which
may have an associated alias (i.e. a short abbreviation).
The namespaces (including their aliases) that are used in a XML document
must be defined within the root element.
Also, when there are multiple namespaces in use, the root element
must define a _defaultNamespace_ to which all elements belong 
if there is no explicit other namespace set.

Example XML file:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<bookstore>

    <book category="cooking">
        <title lang="en">Everyday Italian</title>
        <author>Giada De Laurentiis</author>
        <year>2005</year>
        <price>30.00</price>
    </book>

    <book category="children">
        <title lang="en">Harry Potter</title>
        <author>J K. Rowling</author>
        <year>2005</year>
        <price>29.99</price>
    </book>


    <book category="web" cover="paperback">
        <title lang="en">Learning XML</title>
        <author>Erik T. Ray</author>
        <year>2003</year>
        <price>39.95</price>
    </book>

</bookstore>
```
