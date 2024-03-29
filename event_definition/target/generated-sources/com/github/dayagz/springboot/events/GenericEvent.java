/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.github.dayagz.springboot.events;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class GenericEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"GenericEvent\",\"namespace\":\"com.github.dayagz.springboot.events\",\"fields\":[{\"name\":\"correlation_id\",\"type\":\"string\"},{\"name\":\"event_type\",\"type\":\"string\"},{\"name\":\"event_version\",\"type\":\"string\",\"default\":\"V2.0\"},{\"name\":\"application_id\",\"type\":\"string\"},{\"name\":\"application_module\",\"type\":\"string\"},{\"name\":\"spec_version\",\"type\":\"string\",\"default\":\"V1.0\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence correlation_id;
  @Deprecated public java.lang.CharSequence event_type;
  @Deprecated public java.lang.CharSequence event_version;
  @Deprecated public java.lang.CharSequence application_id;
  @Deprecated public java.lang.CharSequence application_module;
  @Deprecated public java.lang.CharSequence spec_version;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public GenericEvent() {}

  /**
   * All-args constructor.
   */
  public GenericEvent(java.lang.CharSequence correlation_id, java.lang.CharSequence event_type, java.lang.CharSequence event_version, java.lang.CharSequence application_id, java.lang.CharSequence application_module, java.lang.CharSequence spec_version) {
    this.correlation_id = correlation_id;
    this.event_type = event_type;
    this.event_version = event_version;
    this.application_id = application_id;
    this.application_module = application_module;
    this.spec_version = spec_version;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return correlation_id;
    case 1: return event_type;
    case 2: return event_version;
    case 3: return application_id;
    case 4: return application_module;
    case 5: return spec_version;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: correlation_id = (java.lang.CharSequence)value$; break;
    case 1: event_type = (java.lang.CharSequence)value$; break;
    case 2: event_version = (java.lang.CharSequence)value$; break;
    case 3: application_id = (java.lang.CharSequence)value$; break;
    case 4: application_module = (java.lang.CharSequence)value$; break;
    case 5: spec_version = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'correlation_id' field.
   */
  public java.lang.CharSequence getCorrelationId() {
    return correlation_id;
  }

  /**
   * Sets the value of the 'correlation_id' field.
   * @param value the value to set.
   */
  public void setCorrelationId(java.lang.CharSequence value) {
    this.correlation_id = value;
  }

  /**
   * Gets the value of the 'event_type' field.
   */
  public java.lang.CharSequence getEventType() {
    return event_type;
  }

  /**
   * Sets the value of the 'event_type' field.
   * @param value the value to set.
   */
  public void setEventType(java.lang.CharSequence value) {
    this.event_type = value;
  }

  /**
   * Gets the value of the 'event_version' field.
   */
  public java.lang.CharSequence getEventVersion() {
    return event_version;
  }

  /**
   * Sets the value of the 'event_version' field.
   * @param value the value to set.
   */
  public void setEventVersion(java.lang.CharSequence value) {
    this.event_version = value;
  }

  /**
   * Gets the value of the 'application_id' field.
   */
  public java.lang.CharSequence getApplicationId() {
    return application_id;
  }

  /**
   * Sets the value of the 'application_id' field.
   * @param value the value to set.
   */
  public void setApplicationId(java.lang.CharSequence value) {
    this.application_id = value;
  }

  /**
   * Gets the value of the 'application_module' field.
   */
  public java.lang.CharSequence getApplicationModule() {
    return application_module;
  }

  /**
   * Sets the value of the 'application_module' field.
   * @param value the value to set.
   */
  public void setApplicationModule(java.lang.CharSequence value) {
    this.application_module = value;
  }

  /**
   * Gets the value of the 'spec_version' field.
   */
  public java.lang.CharSequence getSpecVersion() {
    return spec_version;
  }

  /**
   * Sets the value of the 'spec_version' field.
   * @param value the value to set.
   */
  public void setSpecVersion(java.lang.CharSequence value) {
    this.spec_version = value;
  }

  /** Creates a new GenericEvent RecordBuilder */
  public static com.github.dayagz.springboot.events.GenericEvent.Builder newBuilder() {
    return new com.github.dayagz.springboot.events.GenericEvent.Builder();
  }
  
  /** Creates a new GenericEvent RecordBuilder by copying an existing Builder */
  public static com.github.dayagz.springboot.events.GenericEvent.Builder newBuilder(com.github.dayagz.springboot.events.GenericEvent.Builder other) {
    return new com.github.dayagz.springboot.events.GenericEvent.Builder(other);
  }
  
  /** Creates a new GenericEvent RecordBuilder by copying an existing GenericEvent instance */
  public static com.github.dayagz.springboot.events.GenericEvent.Builder newBuilder(com.github.dayagz.springboot.events.GenericEvent other) {
    return new com.github.dayagz.springboot.events.GenericEvent.Builder(other);
  }
  
  /**
   * RecordBuilder for GenericEvent instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<GenericEvent>
    implements org.apache.avro.data.RecordBuilder<GenericEvent> {

    private java.lang.CharSequence correlation_id;
    private java.lang.CharSequence event_type;
    private java.lang.CharSequence event_version;
    private java.lang.CharSequence application_id;
    private java.lang.CharSequence application_module;
    private java.lang.CharSequence spec_version;

    /** Creates a new Builder */
    private Builder() {
      super(com.github.dayagz.springboot.events.GenericEvent.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.github.dayagz.springboot.events.GenericEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.correlation_id)) {
        this.correlation_id = data().deepCopy(fields()[0].schema(), other.correlation_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.event_type)) {
        this.event_type = data().deepCopy(fields()[1].schema(), other.event_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.event_version)) {
        this.event_version = data().deepCopy(fields()[2].schema(), other.event_version);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.application_id)) {
        this.application_id = data().deepCopy(fields()[3].schema(), other.application_id);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.application_module)) {
        this.application_module = data().deepCopy(fields()[4].schema(), other.application_module);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.spec_version)) {
        this.spec_version = data().deepCopy(fields()[5].schema(), other.spec_version);
        fieldSetFlags()[5] = true;
      }
    }
    
    /** Creates a Builder by copying an existing GenericEvent instance */
    private Builder(com.github.dayagz.springboot.events.GenericEvent other) {
            super(com.github.dayagz.springboot.events.GenericEvent.SCHEMA$);
      if (isValidValue(fields()[0], other.correlation_id)) {
        this.correlation_id = data().deepCopy(fields()[0].schema(), other.correlation_id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.event_type)) {
        this.event_type = data().deepCopy(fields()[1].schema(), other.event_type);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.event_version)) {
        this.event_version = data().deepCopy(fields()[2].schema(), other.event_version);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.application_id)) {
        this.application_id = data().deepCopy(fields()[3].schema(), other.application_id);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.application_module)) {
        this.application_module = data().deepCopy(fields()[4].schema(), other.application_module);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.spec_version)) {
        this.spec_version = data().deepCopy(fields()[5].schema(), other.spec_version);
        fieldSetFlags()[5] = true;
      }
    }

    /** Gets the value of the 'correlation_id' field */
    public java.lang.CharSequence getCorrelationId() {
      return correlation_id;
    }
    
    /** Sets the value of the 'correlation_id' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder setCorrelationId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.correlation_id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'correlation_id' field has been set */
    public boolean hasCorrelationId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'correlation_id' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder clearCorrelationId() {
      correlation_id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'event_type' field */
    public java.lang.CharSequence getEventType() {
      return event_type;
    }
    
    /** Sets the value of the 'event_type' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder setEventType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.event_type = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'event_type' field has been set */
    public boolean hasEventType() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'event_type' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder clearEventType() {
      event_type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'event_version' field */
    public java.lang.CharSequence getEventVersion() {
      return event_version;
    }
    
    /** Sets the value of the 'event_version' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder setEventVersion(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.event_version = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'event_version' field has been set */
    public boolean hasEventVersion() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'event_version' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder clearEventVersion() {
      event_version = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'application_id' field */
    public java.lang.CharSequence getApplicationId() {
      return application_id;
    }
    
    /** Sets the value of the 'application_id' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder setApplicationId(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.application_id = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'application_id' field has been set */
    public boolean hasApplicationId() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'application_id' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder clearApplicationId() {
      application_id = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'application_module' field */
    public java.lang.CharSequence getApplicationModule() {
      return application_module;
    }
    
    /** Sets the value of the 'application_module' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder setApplicationModule(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.application_module = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'application_module' field has been set */
    public boolean hasApplicationModule() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'application_module' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder clearApplicationModule() {
      application_module = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'spec_version' field */
    public java.lang.CharSequence getSpecVersion() {
      return spec_version;
    }
    
    /** Sets the value of the 'spec_version' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder setSpecVersion(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.spec_version = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'spec_version' field has been set */
    public boolean hasSpecVersion() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'spec_version' field */
    public com.github.dayagz.springboot.events.GenericEvent.Builder clearSpecVersion() {
      spec_version = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    public GenericEvent build() {
      try {
        GenericEvent record = new GenericEvent();
        record.correlation_id = fieldSetFlags()[0] ? this.correlation_id : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.event_type = fieldSetFlags()[1] ? this.event_type : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.event_version = fieldSetFlags()[2] ? this.event_version : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.application_id = fieldSetFlags()[3] ? this.application_id : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.application_module = fieldSetFlags()[4] ? this.application_module : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.spec_version = fieldSetFlags()[5] ? this.spec_version : (java.lang.CharSequence) defaultValue(fields()[5]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
