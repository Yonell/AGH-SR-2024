// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: calculator.proto
// Protobuf Java Version: 4.26.1

package sr.grpc.gen;

public final class CalculatorProto {
  private CalculatorProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 1,
      /* suffix= */ "",
      CalculatorProto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_m1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_calculator_m1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_calculator_Empty_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_calculator_Empty_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020calculator.proto\022\ncalculator\" \n\002m1\022\021\n\004" +
      "val1\030\001 \001(\005H\000\210\001\001B\007\n\005_val1\"\007\n\005Empty2d\n\006Con" +
      "fig\022,\n\005test1\022\021.calculator.Empty\032\016.calcul" +
      "ator.m1\"\000\022,\n\005test2\022\016.calculator.m1\032\021.cal" +
      "culator.Empty\"\000B \n\013sr.grpc.genB\017Calculat" +
      "orProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_calculator_m1_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_calculator_m1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_calculator_m1_descriptor,
        new java.lang.String[] { "Val1", });
    internal_static_calculator_Empty_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_calculator_Empty_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_calculator_Empty_descriptor,
        new java.lang.String[] { });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
