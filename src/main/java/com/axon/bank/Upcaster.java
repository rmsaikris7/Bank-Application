package com.axon.bank;

import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;

import com.axon.bank.apis.events.BankAccountCreated;

public class Upcaster extends SingleEventUpcaster {

	private static final SimpleSerializedType TARGET_TYPE =
	           new SimpleSerializedType(BankAccountCreated.class.getTypeName(), "1.0");

	   @Override
	   protected boolean canUpcast(IntermediateEventRepresentation intermediateRepresentation) {
	      return intermediateRepresentation.getType().equals(TARGET_TYPE);
	   }

	   @Override
	   protected IntermediateEventRepresentation doUpcast(
	           IntermediateEventRepresentation intermediateRepresentation
	   ) {
	      return intermediateRepresentation.upcastPayload(
	              new SimpleSerializedType(TARGET_TYPE.getName(), "2.0"),
	              org.dom4j.Document.class,
	              document -> {
	                 document.getRootElement()
	                         .addElement("comment")
	                         .setText("no comment");
	                 return document;
	              }
	      );
	   }
	}