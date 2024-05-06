# -*- coding: utf-8 -*-
#
# Copyright (c) ZeroC, Inc. All rights reserved.
#
#
# Ice version 3.7.10
#
# <auto-generated>
#
# Generated from file `home.ice'
#
# Warning: do not edit this file.
#
# </auto-generated>
#

from sys import version_info as _version_info_
import Ice, IcePy

# Start of module home
_M_home = Ice.openModule('home')
__name__ = 'home'

if 'c1' not in _M_home.__dict__:
    _M_home.c1 = Ice.createTempClass()
    class c1(Ice.Value):
        def __init__(self, val1=0, val2=Ice.Unset):
            self.val1 = val1
            self.val2 = val2

        def ice_id(self):
            return '::home::c1'

        @staticmethod
        def ice_staticId():
            return '::home::c1'

        def __str__(self):
            return IcePy.stringify(self, _M_home._t_c1)

        __repr__ = __str__

    _M_home._t_c1 = IcePy.defineValue('::home::c1', c1, -1, (), False, False, None, (
        ('val1', (), IcePy._t_int, False, 0),
        ('val2', (), IcePy._t_int, True, 2)
    ))
    c1._ice_type = _M_home._t_c1

    _M_home.c1 = c1
    del c1

if 'e1' not in _M_home.__dict__:
    _M_home.e1 = Ice.createTempClass()
    class e1(Ice.UserException):
        def __init__(self, m=Ice.Unset):
            self.m = m

        def __str__(self):
            return IcePy.stringifyException(self)

        __repr__ = __str__

        _ice_id = '::home::e1'

    _M_home._t_e1 = IcePy.defineException('::home::e1', e1, (), False, None, (('m', (), IcePy._t_int, True, 3),))
    e1._ice_type = _M_home._t_e1

    _M_home.e1 = e1
    del e1

_M_home._t_Config = IcePy.defineValue('::home::Config', Ice.Value, -1, (), False, True, None, ())

if 'ConfigPrx' not in _M_home.__dict__:
    _M_home.ConfigPrx = Ice.createTempClass()
    class ConfigPrx(Ice.ObjectPrx):

        def test1(self, context=None):
            return _M_home.Config._op_test1.invoke(self, ((), context))

        def test1Async(self, context=None):
            return _M_home.Config._op_test1.invokeAsync(self, ((), context))

        def begin_test1(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_home.Config._op_test1.begin(self, ((), _response, _ex, _sent, context))

        def end_test1(self, _r):
            return _M_home.Config._op_test1.end(self, _r)

        def test2(self, arg=Ice.Unset, context=None):
            return _M_home.Config._op_test2.invoke(self, ((arg, ), context))

        def test2Async(self, arg, context=None):
            return _M_home.Config._op_test2.invokeAsync(self, ((arg, ), context))

        def begin_test2(self, arg, _response=None, _ex=None, _sent=None, context=None):
            return _M_home.Config._op_test2.begin(self, ((arg, ), _response, _ex, _sent, context))

        def end_test2(self, _r):
            return _M_home.Config._op_test2.end(self, _r)

        def test3(self, arg, context=None):
            return _M_home.Config._op_test3.invoke(self, ((arg, ), context))

        def test3Async(self, arg, context=None):
            return _M_home.Config._op_test3.invokeAsync(self, ((arg, ), context))

        def begin_test3(self, arg, _response=None, _ex=None, _sent=None, context=None):
            return _M_home.Config._op_test3.begin(self, ((arg, ), _response, _ex, _sent, context))

        def end_test3(self, _r):
            return _M_home.Config._op_test3.end(self, _r)

        def test4(self, context=None):
            return _M_home.Config._op_test4.invoke(self, ((), context))

        def test4Async(self, context=None):
            return _M_home.Config._op_test4.invokeAsync(self, ((), context))

        def begin_test4(self, _response=None, _ex=None, _sent=None, context=None):
            return _M_home.Config._op_test4.begin(self, ((), _response, _ex, _sent, context))

        def end_test4(self, _r):
            return _M_home.Config._op_test4.end(self, _r)

        @staticmethod
        def checkedCast(proxy, facetOrContext=None, context=None):
            return _M_home.ConfigPrx.ice_checkedCast(proxy, '::home::Config', facetOrContext, context)

        @staticmethod
        def uncheckedCast(proxy, facet=None):
            return _M_home.ConfigPrx.ice_uncheckedCast(proxy, facet)

        @staticmethod
        def ice_staticId():
            return '::home::Config'
    _M_home._t_ConfigPrx = IcePy.defineProxy('::home::Config', ConfigPrx)

    _M_home.ConfigPrx = ConfigPrx
    del ConfigPrx

    _M_home.Config = Ice.createTempClass()
    class Config(Ice.Object):

        def ice_ids(self, current=None):
            return ('::Ice::Object', '::home::Config')

        def ice_id(self, current=None):
            return '::home::Config'

        @staticmethod
        def ice_staticId():
            return '::home::Config'

        def test1(self, current=None):
            raise NotImplementedError("servant method 'test1' not implemented")

        def test2(self, arg, current=None):
            raise NotImplementedError("servant method 'test2' not implemented")

        def test3(self, arg, current=None):
            raise NotImplementedError("servant method 'test3' not implemented")

        def test4(self, current=None):
            raise NotImplementedError("servant method 'test4' not implemented")

        def __str__(self):
            return IcePy.stringify(self, _M_home._t_ConfigDisp)

        __repr__ = __str__

    _M_home._t_ConfigDisp = IcePy.defineClass('::home::Config', Config, (), None, ())
    Config._ice_type = _M_home._t_ConfigDisp

    Config._op_test1 = IcePy.Operation('test1', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), ((), IcePy._t_int, True, 0), ())
    Config._op_test2 = IcePy.Operation('test2', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), IcePy._t_int, True, 1),), (), None, ())
    Config._op_test3 = IcePy.Operation('test3', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (((), _M_home._t_c1, False, 0),), (), None, ())
    Config._op_test4 = IcePy.Operation('test4', Ice.OperationMode.Normal, Ice.OperationMode.Normal, False, None, (), (), (), None, (_M_home._t_e1,))

    _M_home.Config = Config
    del Config

# End of module home