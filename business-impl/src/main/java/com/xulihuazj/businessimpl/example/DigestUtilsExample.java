/*
 * DigestUtilsExample.java 1.0.0 2018/01/26  14:08 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2018/01/26  14:08 created by xulihua
 */
package com.xulihuazj.businessimpl.example;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtilsExample {


//    // Returns a <code>MessageDigest</code> for the given <code>algorithm</code>.
//    MessageDigest getDigest(final String algorithm)
//
//
//    // Returns an MD2 MessageDigest.
//    MessageDigest getMd2Digest() {
//        return getDigest(MessageDigestAlgorithms.MD2);
//    }
//
//
//    // Returns an MD5 MessageDigest.
//
//    MessageDigest getMd5Digest()
//
//    // Returns an SHA-1 digest.
//    MessageDigest getSha1Digest()
//
//    // Returns an SHA-256 digest.
//    MessageDigest getSha256Digest()
//
//    //Returns an SHA-384 digest.
//    MessageDigest getSha384Digest()
//
//    //Returns an SHA-512 digest.
//    MessageDigest getSha512Digest()
//
//    // Returns an SHA-1 digest.
//    MessageDigest getShaDigest()
//
//    //Calculates the MD2 digest and returns the value as a 16 element <code>byte[]</code>.=
//    byte[] md2
//
//    // Calculates the MD2 digest and returns the value as a 16 element <code>byte[]</code>.
//    byte[] md2(final InputStream data)
//
//    // Calculates the MD2 digest and returns the value as a 16 element <code>byte[]</code>.
//    byte[] md2(final String data)
//
//    // Calculates the MD2 digest and returns the value as a 32 character hex string.
//    String md2Hex(final byte[] data)
//
//    // Calculates the MD2 digest and returns the value as a 32 character hex string.
//    String md2Hex(final InputStream data)
//
//    // Calculates the MD2 digest and returns the value as a 32 character hex string.
//    String md2Hex(final String data)
//
//    // Calculates the MD5 digest and returns the value as a 16 element <code>byte[]</code>.
//    byte[] md5(final byte[] data) {
//        return getMd5Digest().digest(data);
//    }
//
//     *
//    Calculates the
//    MD5 digest
//    and returns
//    the value
//    as a 16element<code>byte[]</code>.
//
//    byte[] md5(final InputStream data)
//
//     *
//    Calculates the
//    MD5 digest
//    and returns
//    the value
//    as a 16element<code>byte[]</code>.
//
//    byte[] md5(final String data)
//
//     *
//    Calculates the
//    MD5 digest
//    and returns
//    the value
//    as a 32
//    character hex
//
//    string.
//            String md5Hex(final byte[] data) {
//        return Hex.encodeHexString(md5(data));
//    }
//
//
//     *
//    Calculates the
//    MD5 digest
//    and returns
//    the value
//    as a 32
//    character hex
//
//    string.
//            String md5Hex(final InputStream data)
//
//
//     *
//    Calculates the
//    MD5 digest
//    and returns
//    the value
//    as a 32
//    character hex
//
//    string.
//            String md5Hex(final String data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a<code>byte[]</code>.
//
//    byte[] sha(final byte[] data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a<code>byte[]</code>.
//
//    byte[] sha(final InputStream data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a<code>byte[]</code>.
//
//    byte[] sha(final String data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a<code>byte[]</code>.
//
//    byte[] sha1(final byte[] data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a<code>byte[]</code>.
//
//    byte[] sha1(final InputStream data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a<code>byte[]</code>.
//
//    byte[] sha1(final String data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a hex
//
//    string.
//            String sha1Hex(final byte[] data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a hex
//
//    string.
//            String sha1Hex(final InputStream data)
//
//     *
//    Calculates the
//    SHA-1
//    digest and
//    returns the
//    value as
//    a hex
//
//    string.
//            String sha1Hex(final String data) {
//
//     *Calculates the SHA - 256 digest and returns the value as a<code>byte[]</code >.
//        byte[] sha256 ( final byte[] data){
//
//     *Calculates the SHA - 256 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha256 ( final InputStream data)
//
//     *Calculates the SHA - 256 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha256 ( final String data)
//
//             *Calculates the SHA - 256 digest and returns the value as a hex string.
//                    String sha256Hex( final byte[] data)
//
//             *Calculates the SHA - 256 digest and returns the value as a hex string.
//                    String sha256Hex( final InputStream data)
//
//             *Calculates the SHA - 256 digest and returns the value as a hex string.
//                    String sha256Hex( final String data)
//
//             *Calculates the SHA - 384 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha384 ( final byte[] data)
//
//     *Calculates the SHA - 384 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha384 ( final InputStream data)
//
//
//     *Calculates the SHA - 384 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha384 ( final String data)
//
//     *Calculates the SHA - 384 digest and returns the value as a hex string.
//                    String sha384Hex( final byte[] data)
//
//
//     *Calculates the SHA - 384 digest and returns the value as a hex string.
//                    String sha384Hex( final InputStream data)
//
//
//     *Calculates the SHA - 384 digest and returns the value as a hex string.
//                    String sha384Hex( final String data)
//
//
//     *Calculates the SHA - 512 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha512 ( final byte[] data)
//
//     *Calculates the SHA - 512 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha512 ( final InputStream data)
//
//     *Calculates the SHA - 512 digest and returns the value as a<code>byte[]</code >.
//            byte[] sha512 ( final String data)
//
//     *Calculates the SHA - 512 digest and returns the value as a hex string.
//                    String sha512Hex( final byte[] data)
//
//     *Calculates the SHA - 512 digest and returns the value as a hex string.
//                    String sha512Hex( final InputStream data)
//
//     *Calculates the SHA - 512 digest and returns the value as a hex string.
//                    String sha512Hex( final String data){
//                return Hex.encodeHexString(sha512(data));
//            }
//
//     *Calculates the SHA - 1 digest and returns the value as a hex string.
//                    String shaHex( final byte[] data)
//
//     *Calculates the SHA - 1 digest and returns the value as a hex string.
//                    String shaHex( final InputStream data)
//
//     *Calculates the SHA - 1 digest and returns the value as a hex string.
//                    String shaHex( final String data)
//
//     *Updates the given
//            MessageDigest updateDigest ( final MessageDigest messageDigest, final byte[] valueToDigest)
//
//     *Reads through an InputStream and updates the digest for the data
//            MessageDigest updateDigest ( final MessageDigest digest, final InputStream data)
//
//     *Updates the given
//        MessageDigest updateDigest ( final MessageDigest messageDigest, final String valueToDigest)

    @Test
    public void test1() throws UnsupportedEncodingException {
     String str = "aaaa";
        byte[] bytes = DigestUtils.md5(str);
        String bbb = new String(bytes,"utf-8");
    }

    }
