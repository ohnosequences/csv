package ohnosequences.csv

import ohnosequences.cosas._, properties._, records._, types._, typeSets._

object stuff {

  /*
    ## Raw representations of records

    Our concept of a raw representation for a record value consists of

    1. a type `RV` which represents the _raw_ type to which all _values_ can be serialized and parsed from.
    2. a type `RK` which represents the _raw_ type to which all _keys_ can be serialized and parsed from.

    Given that, we need to have `Property`-specific functions

    1. `serialize: ValueOf[P] => RV`
    2. `parse: RV => Either[ParseError[P], ValueOf[P]]`

    We can guarantee statically (given a record) that we have onesuch pair of functions for each property.

    The type representing the raw record is (should be?) `Map[RV,RK]`. This way we can distinguish between parsing errors and data errors (`raw.get(key)` is `None`).
  */
  trait AnyRawFormat {

    type RK
    type RV
  }

  trait AnyPropertyParser {

    type P <: AnyProperty
    val p: P

    type From

    def parse(from: From): Option[ValueOf[P]]
  }

  trait AnyParseError
  case class ErrorParsingWith[PP <: AnyPropertyParser](parser: PP, from: PP#From) extends AnyParseError

  trait AnyPropertySerializer {

    type P <: AnyProperty
    val p: P

    type To

    def serialize(pv: ValueOf[P]): To
  }

  trait AnyRecordRepresentation {

    type Record <: AnyRecord
    val record: Record

    type Format <: AnyRawFormat

    type PropertyParsers <: AnyTypeSet.Of[AnyPropertyParser { type From = Format#RV }]
    val propertyParsers: PropertyParsers

    type PropertySerializers <: AnyTypeSet.Of[AnyPropertySerializer { type To = Format#RV }]
    val PropertySerializers: PropertySerializers
  }
}
