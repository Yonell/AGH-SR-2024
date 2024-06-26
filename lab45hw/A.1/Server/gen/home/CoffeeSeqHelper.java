//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `home.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package home;

/**
 * Helper class for marshaling/unmarshaling CoffeeSeq.
 **/
public final class CoffeeSeqHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, coffeeType[] v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.length);
            for(int i0 = 0; i0 < v.length; i0++)
            {
                coffeeType.ice_write(ostr, v[i0]);
            }
        }
    }

    public static coffeeType[] read(com.zeroc.Ice.InputStream istr)
    {
        final coffeeType[] v;
        final int len0 = istr.readAndCheckSeqSize(1);
        v = new coffeeType[len0];
        for(int i0 = 0; i0 < len0; i0++)
        {
            v[i0] = coffeeType.ice_read(istr);
        }
        return v;
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<coffeeType[]> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, coffeeType[] v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            CoffeeSeqHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    public static java.util.Optional<coffeeType[]> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            coffeeType[] v;
            v = CoffeeSeqHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
