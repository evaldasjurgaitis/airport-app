import React from 'react';
import { Modal as ModalBase } from 'antd';
import { ModalProps } from 'antd/lib/modal';

import 'antd/lib/modal/style/index.less';

interface Props extends ModalProps {
    children?: React.ReactNode;
    onClose?: () => void;
    visible: boolean;
    width?: number;
    closable?: boolean;
    maskClosable?: boolean;
    size?: string;
}

const Modal = ({ children, onClose, visible, width = 700, closable = true, maskClosable = true, size }: Props) => {
    const modalWith = size === 'sm' ? 500 : width;

    return (
        <ModalBase
            destroyOnClose
            visible={visible}
            width={modalWith}
            onCancel={onClose}
            closable={closable}
            maskClosable={maskClosable}
            centered
            footer={false}
        >
            {children}
        </ModalBase>
    );
};

export default Modal;
